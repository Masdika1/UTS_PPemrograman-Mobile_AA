package com.example.utsdika.kalkulator

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.utsdika.ui.theme.UTSdikaTheme
import kotlin.math.*

class CalculatorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                UTSdikaTheme {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var displayText by remember { mutableStateOf("0") }
    var historyText by remember { mutableStateOf("") }
    var currentNumber by remember { mutableStateOf("") }
    var previousNumber by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var isNewOperation by remember { mutableStateOf(true) }
    var lastResult by remember { mutableStateOf("") }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    fun performCalculation(): String {
        if (previousNumber.isEmpty() || currentNumber.isEmpty() || operation.isEmpty()) {
            return currentNumber.ifEmpty { "0" }
        }

        val num1 = previousNumber.toDoubleOrNull() ?: return "Error"
        val num2 = currentNumber.toDoubleOrNull() ?: return "Error"

        val result = when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "×" -> num1 * num2
            "÷" -> if (num2 != 0.0) num1 / num2 else return "Error"
            "xʸ" -> num1.pow(num2)
            else -> return "Error"
        }

        return if (result % 1.0 == 0.0) {
            result.toLong().toString()
        } else {
            result.toString()
        }
    }

    fun performScientificOperation(op: String): String {
        val num = currentNumber.toDoubleOrNull() ?: 0.0

        val result = when (op) {
            "sin" -> sin(Math.toRadians(num))
            "cos" -> cos(Math.toRadians(num))
            "tan" -> tan(Math.toRadians(num))
            "ln" -> if (num > 0) ln(num) else return "Error"
            "log" -> if (num > 0) log10(num) else return "Error"
            "√" -> if (num >= 0) sqrt(num) else return "Error"
            "x²" -> num * num
            "x³" -> num * num * num
            "1/x" -> if (num != 0.0) 1 / num else return "Error"
            "eˣ" -> E.pow(num)
            "10ˣ" -> 10.0.pow(num)
            "|x|" -> abs(num)
            "EE" -> num * 10.0
            "e" -> E
            "π" -> PI
            else -> return "Error"
        }

        return if (result.isFinite()) {
            if (result % 1.0 == 0.0 && abs(result) < 1e10) {
                result.toLong().toString()
            } else {
                String.format("%.10f", result).trimEnd('0').trimEnd('.')
            }
        } else {
            "Error"
        }
    }

    fun updateHistoryDisplay() {
        historyText = when {
            previousNumber.isNotEmpty() && operation.isNotEmpty() && currentNumber.isNotEmpty() -> {
                "$previousNumber $operation $currentNumber"
            }
            previousNumber.isNotEmpty() && operation.isNotEmpty() -> {
                "$previousNumber $operation"
            }
            else -> ""
        }
    }

    fun handleNumberInput(number: String) {
        if (isNewOperation) {
            currentNumber = number
            displayText = number
            isNewOperation = false
        } else {
            currentNumber += number
            displayText = currentNumber
        }
        updateHistoryDisplay()
    }

    fun handleOperationInput(op: String) {
        if (currentNumber.isNotEmpty()) {
            if (previousNumber.isNotEmpty() && operation.isNotEmpty()) {
                val result = performCalculation()
                previousNumber = result
                displayText = result
                currentNumber = ""
            } else {
                previousNumber = currentNumber
                currentNumber = ""
            }
            operation = op
            isNewOperation = false
        } else if (previousNumber.isNotEmpty()) {
            operation = op
        }
        updateHistoryDisplay()
    }

    fun handleEquals() {
        if (previousNumber.isNotEmpty() && currentNumber.isNotEmpty() && operation.isNotEmpty()) {
            val result = performCalculation()
            displayText = result
            lastResult = result
            historyText = ""
            previousNumber = ""
            currentNumber = result
            operation = ""
            isNewOperation = true
        }
    }

    fun handleClear() {
        displayText = "0"
        historyText = ""
        currentNumber = ""
        previousNumber = ""
        operation = ""
        isNewOperation = true
    }

    fun handleBackspace() {
        if (currentNumber.isNotEmpty() && !isNewOperation) {
            currentNumber = currentNumber.dropLast(1)
            displayText = if (currentNumber.isEmpty()) "0" else currentNumber
            updateHistoryDisplay()
        }
    }

    fun handleDecimal() {
        if (isNewOperation) {
            currentNumber = "0."
            displayText = "0."
            isNewOperation = false
        } else if (!currentNumber.contains(".")) {
            currentNumber += "."
            displayText = currentNumber
        }
        updateHistoryDisplay()
    }

    fun handlePercentage() {
        if (currentNumber.isNotEmpty()) {
            val num = currentNumber.toDoubleOrNull() ?: return
            val result = num / 100
            currentNumber = result.toString()
            displayText = currentNumber
            updateHistoryDisplay()
        }
    }

    fun handlePlusMinus() {
        if (currentNumber.isNotEmpty() && currentNumber != "0") {
            currentNumber = if (currentNumber.startsWith("-")) {
                currentNumber.substring(1)
            } else {
                "-$currentNumber"
            }
            displayText = currentNumber
            updateHistoryDisplay()
        }
    }

    fun handleScientific(op: String) {
        if (op == "e" || op == "π") {
            // Konstanta: langsung set nilai
            val result = performScientificOperation(op)
            if (isNewOperation) {
                currentNumber = result
                displayText = result
                historyText = ""
                isNewOperation = false
            } else {
                // Jika sudah ada angka, ganti dengan konstanta
                currentNumber = result
                displayText = result
                historyText = ""
                isNewOperation = false
            }
        } else if (currentNumber.isNotEmpty()) {
            // Operasi scientific: tampilkan operasi dulu, lalu hasil
            val inputValue = currentNumber

            // Format history berdasarkan jenis operasi
            historyText = when (op) {
                "√" -> "$op($inputValue)"
                "x²" -> "($inputValue)²"
                "x³" -> "($inputValue)³"
                "1/x" -> "1/($inputValue)"
                "eˣ" -> "e^($inputValue)"
                "10ˣ" -> "10^($inputValue)"
                "|x|" -> "|$inputValue|"
                "EE" -> "${inputValue}E"
                else -> "$op($inputValue)"  // sin, cos, tan, ln, log
            }

            val result = performScientificOperation(op)
            currentNumber = result
            displayText = result
            isNewOperation = true  // Set true agar angka berikutnya replace hasil
        } else {
            // Jika tidak ada input, tampilkan pesan atau set default
            historyText = when (op) {
                "√" -> "$op(0)"
                "x²" -> "(0)²"
                "x³" -> "(0)³"
                "1/x" -> "1/(0)"
                "eˣ" -> "e^(0)"
                "10ˣ" -> "10^(0)"
                "|x|" -> "|0|"
                "EE" -> "0E"
                else -> "$op(0)"
            }
            currentNumber = "0"
            val result = performScientificOperation(op)
            currentNumber = result
            displayText = result
            isNewOperation = true
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1C1C1E)
    ) {
        if (isLandscape) {
            // ✅ LANDSCAPE LAYOUT - Display terlihat jelas
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // ===== KOLOM 1: Scientific kiri =====
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .padding(top = 95.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            ScientificButton("sin", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("sin") }
                            ScientificButton("cos", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("cos") }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            ScientificButton("ln", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("ln") }
                            ScientificButton("log", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("log") }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            ScientificButton("xʸ", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleOperationInput("xʸ") }
                            ScientificButton("x²", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("x²") }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            ScientificButton("|x|", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("|x|") }
                            ScientificButton("π", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("π") }
                        }
                    }
                }

                Spacer(modifier = Modifier.width(4.dp))

                // ===== KOLOM 2: Scientific kanan =====
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .padding(top = 92.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            ScientificButton("tan", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("tan") }
                            ScientificButton("√", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("√") }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            ScientificButton("eˣ", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("eˣ") }
                            ScientificButton("10ˣ", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("10ˣ") }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            ScientificButton("x³", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("x³") }
                            ScientificButton("1/x", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("1/x") }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            ScientificButton("e", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("e") }
                            ScientificButton("EE", Color(0xFF2C2C2E), Color.White, Modifier.weight(1f)) { handleScientific("EE") }
                        }
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // ===== KOLOM 3: Display + kalkulator utama =====
                Column(
                    modifier = Modifier
                        .weight(1.8f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Top
                ) {
                    // === Display ===
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(end = 8.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End
                    ) {
                        if (historyText.isNotEmpty()) {
                            Text(
                                text = historyText,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                color = Color(0xFF4DB56A),
                                textAlign = TextAlign.End,
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }

                        Text(
                            text = displayText,
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                            textAlign = TextAlign.End,
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // === Tombol kalkulator ===
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            CalculatorButton("C", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), 32.dp) { handleClear() }
                            CalculatorButton("⌫", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), 32.dp) { handleBackspace() }
                            CalculatorButton("%", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), 32.dp) { handlePercentage() }
                            CalculatorButton("÷", Color(0xFF4DB56A), Color.White, Modifier.weight(1f), 32.dp) { handleOperationInput("÷") }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            CalculatorButton("7", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handleNumberInput("7") }
                            CalculatorButton("8", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handleNumberInput("8") }
                            CalculatorButton("9", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handleNumberInput("9") }
                            CalculatorButton("×", Color(0xFF4DB56A), Color.White, Modifier.weight(1f), 32.dp) { handleOperationInput("×") }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            CalculatorButton("4", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handleNumberInput("4") }
                            CalculatorButton("5", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handleNumberInput("5") }
                            CalculatorButton("6", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handleNumberInput("6") }
                            CalculatorButton("-", Color(0xFF4DB56A), Color.White, Modifier.weight(1f), 32.dp) { handleOperationInput("-") }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            CalculatorButton("1", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handleNumberInput("1") }
                            CalculatorButton("2", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handleNumberInput("2") }
                            CalculatorButton("3", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handleNumberInput("3") }
                            CalculatorButton("+", Color(0xFF4DB56A), Color.White, Modifier.weight(1f), 32.dp) { handleOperationInput("+") }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            CalculatorButton("+/-", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handlePlusMinus() }
                            CalculatorButton("0", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handleNumberInput("0") }
                            CalculatorButton(".", Color(0xFF303136), Color.White, Modifier.weight(1f), 32.dp) { handleDecimal() }
                            CalculatorButton("=", Color(0xFF4DB56A), Color.White, Modifier.weight(1f), 32.dp) { handleEquals() }
                        }
                    }
                }
            }
        } else {
            // PORTRAIT LAYOUT (Original)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 32.dp
                    ),
                verticalArrangement = Arrangement.Bottom
            ) {
                // Display
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        // History text
                        if (historyText.isNotEmpty()) {
                            Text(
                                text = historyText,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Gray,
                                textAlign = TextAlign.End,
                                maxLines = 1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }

                        // Result text
                        Text(
                            text = displayText,
                            fontSize = 64.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                            textAlign = TextAlign.End,
                            maxLines = 1
                        )
                    }
                }

                // Buttons
                PortraitCalculatorButtons(
                    onNumberClick = { handleNumberInput(it) },
                    onOperationClick = { handleOperationInput(it) },
                    onEqualsClick = { handleEquals() },
                    onClearClick = { handleClear() },
                    onBackspaceClick = { handleBackspace() },
                    onDecimalClick = { handleDecimal() },
                    onPercentageClick = { handlePercentage() },
                    onPlusMinusClick = { handlePlusMinus() }
                )
            }
        }
    }
}

@Composable
fun PortraitCalculatorButtons(
    onNumberClick: (String) -> Unit,
    onOperationClick: (String) -> Unit,
    onEqualsClick: () -> Unit,
    onClearClick: () -> Unit,
    onBackspaceClick: () -> Unit,
    onDecimalClick: () -> Unit,
    onPercentageClick: () -> Unit,
    onPlusMinusClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Row 1
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CalculatorButton("C", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), 70.dp) { onClearClick() }
            CalculatorButton("⌫", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), 70.dp) { onBackspaceClick() }
            CalculatorButton("%", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), 70.dp) { onPercentageClick() }
            CalculatorButton("÷", Color(0xFF4DB56A), Color.White, Modifier.weight(1f), 70.dp) { onOperationClick("÷") }
        }

        // Row 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CalculatorButton("7", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onNumberClick("7") }
            CalculatorButton("8", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onNumberClick("8") }
            CalculatorButton("9", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onNumberClick("9") }
            CalculatorButton("×", Color(0xFF4DB56A), Color.White, Modifier.weight(1f), 70.dp) { onOperationClick("×") }
        }

        // Row 3
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CalculatorButton("4", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onNumberClick("4") }
            CalculatorButton("5", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onNumberClick("5") }
            CalculatorButton("6", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onNumberClick("6") }
            CalculatorButton("-", Color(0xFF4DB56A), Color.White, Modifier.weight(1f), 70.dp) { onOperationClick("-") }
        }

        // Row 4
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CalculatorButton("1", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onNumberClick("1") }
            CalculatorButton("2", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onNumberClick("2") }
            CalculatorButton("3", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onNumberClick("3") }
            CalculatorButton("+", Color(0xFF4DB56A), Color.White, Modifier.weight(1f), 70.dp) { onOperationClick("+") }
        }

        // Row 5
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CalculatorButton("+/-", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onPlusMinusClick() }
            CalculatorButton("0", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onNumberClick("0") }
            CalculatorButton(".", Color(0xFF303136), Color.White, Modifier.weight(1f), 70.dp) { onDecimalClick() }
            CalculatorButton("=", Color(0xFF4DB56A), Color.White, Modifier.weight(1f), 70.dp) { onEqualsClick() }
        }
    }
}

@Composable
fun ScientificButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(36.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CalculatorButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    height: androidx.compose.ui.unit.Dp = 70.dp,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(height),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            fontSize = if (height < 50.dp) 18.sp else 28.sp,
            fontWeight = FontWeight.Medium,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}