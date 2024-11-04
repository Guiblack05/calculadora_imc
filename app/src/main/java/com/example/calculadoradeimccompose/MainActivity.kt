package com.example.calculadoradeimccompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoradeimccompose.Calculo.CalcularImc
import com.example.calculadoradeimccompose.ui.theme.CalculadoraDeImcComposeTheme
import com.example.calculadoradeimccompose.ui.theme.DARK_BLUE
import com.example.calculadoradeimccompose.ui.theme.WHITE
import com.example.calculadoradeimccompose.ui.theme.LIGHT_BLUE as LIGHT_BLUE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraDeImcComposeTheme {
            CalculadoraImc()
            }
        }
    }

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraImc(){
    val context = LocalContext.current
    var calcularImc = CalcularImc()
    var peso by remember {
        mutableStateOf("")
    }
    var altura by remember {
        mutableStateOf("")
    }
    var textoResultado by remember {
        mutableStateOf("")
    }
    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = LIGHT_BLUE
                ),
                title = {
                    Text(text = "Calculadora de IMC", color = WHITE)
                },
                actions ={
                    IconButton(
                        onClick ={
                            peso=""
                            altura=""
                            textoResultado=""
                        }
                    )  {
                        Image(
                            imageVector = ImageVector.vectorResource(id= R.drawable.ic_refresh),
                            contentDescription = "Icone de Resetr todos os campos"
                        )
                    }
                }

            )
        }
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Calculadora de IMC",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = LIGHT_BLUE,
                modifier = Modifier.padding(50.dp,150.dp)
                )
            OutlinedTextField(
                value = peso,
                onValueChange = {
                    peso= it
                },
                label = {
                    Text("Peso (Kg)")
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                    focusedTextColor = DARK_BLUE,
                    focusedLabelColor = DARK_BLUE
                ),
                textStyle = TextStyle(DARK_BLUE,18.sp),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            OutlinedTextField(
                value = altura,
                onValueChange = {
                    altura= it
                },
                label = {
                    Text("Altura (cm)")
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                    focusedTextColor = DARK_BLUE,
                    focusedLabelColor = DARK_BLUE
                ),
                textStyle = TextStyle(DARK_BLUE,18.sp),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Button(
                onClick = {
                    if(peso.isEmpty() || altura.isEmpty()){
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                    }else{
                        calcularImc.calcularImc(peso,altura)
                        textoResultado = calcularImc.resltadoImc()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    LIGHT_BLUE,
                    contentColor = WHITE
                )

            ) {
                Text(
                    text = "Calcular IMC",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,


                )
            }
            Text(
                text = textoResultado,
                color = DARK_BLUE,
                fontWeight = FontWeight.Bold
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculadoraDeImcComposeTheme {
        CalculadoraImc()
    }
    }
}