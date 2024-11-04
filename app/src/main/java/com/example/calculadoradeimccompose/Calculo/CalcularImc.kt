package com.example.calculadoradeimccompose.Calculo

import java.text.DecimalFormat

class CalcularImc {

    private var resultadoImc=""

    fun calcularImc(peso:String, altura:String){
        val pesoConvertido= peso.toDouble()
        val alturaConvertida= altura.toDouble()
        val resultado: String

        val imc= pesoConvertido/ (alturaConvertida*alturaConvertida)
        val decimalFormat = DecimalFormat("0.00")

        if(imc<=18.5){
            resultado="Peso Baixo \n IMC:${decimalFormat.format(imc)}"
        }else if(imc<=24.9){
                resultado="Peso Normal \n IMC:${decimalFormat.format(imc)}"
            }else if(imc<=29.9){
                    resultado="Sobrepeso \n IMC:${decimalFormat.format(imc)}"
                }else if(imc<=34.9){
                    resultado="Obesidade (Grau I)\n IMC:${decimalFormat.format(imc)}"
                    }else if(imc<=39.9){
                        resultado="Obesidade Severa (Grau II) \n IMC:${decimalFormat.format(imc)}"
                        }else{
                            resultado="Obesidade Severa (Grau III) \n IMC:${decimalFormat.format(imc)}"
        }
        resultadoImc = resultado
    }

    fun resltadoImc(): String{
    return resultadoImc
    }
}