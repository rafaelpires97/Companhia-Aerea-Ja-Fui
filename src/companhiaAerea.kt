import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

fun menu(){
    println("  ✫  Companhia de Aviação “Ja Fui”  ✫  ")
    println("Programa de Reservas")
    println("0 - Sair do programa")
    println("1 – Listar passageiros de um voo")
    println("2 – Número de passageiros de um período de dias")
    println("3 – Escrita em ficheiro")
    println("4 – Análise económica ")
    println("5 – Reserva simples de um voo")
}

fun sair(){
    println("✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫")
    println("✫              ✫ Boa Viagem! ✫               ✫")
    println("✫ Obrigado por escolher a companhia “Ja Fui”  ✫")
    println("✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫")
    exitProcess(0)
}

fun lerVoos(numVoo : Array<String?>, cidadePartida : Array<String?>, cidadeChegada : Array<String?>, diaVoo:Array<Int?>){
    var posicao = 0
    val voos = "voos.txt"
    try {
        val lines = File(voos).readLines()
        for (line in lines) {
            val parts = line.split(':')
            numVoo[posicao] = parts[0]
            cidadePartida[posicao] = parts[1]
            cidadeChegada[posicao] = parts[2]
            diaVoo[posicao]= parts[3].toInt()
            posicao++
        }
        println("Ficheiro de voos lido com sucesso")
    } catch (e: FileNotFoundException) {
        println("Erro: $voos não foi encontrado.")
    }
}

fun lerReservas(numCC: Array<Int?>, nomeReservas: Array<String?>, numVooReservas: Array<String?>, diaReservas: Array<Int?>) {
    var posicao = 0
    val reservas = "reservas.txt"
    try {
        val lines = File(reservas).readLines()
        for (line in lines) {
            val parts = line.split(':')
            numCC[posicao] = parts[0].toInt()
            nomeReservas[posicao] = parts[1]
            numVooReservas[posicao] = parts[2]
            diaReservas[posicao] = parts[3].toInt()
            posicao++
        }
    } catch (e: FileNotFoundException) {
        println("Erro: $reservas não foi encontrado.")
    }
}

fun listarPassageiros(diaReservas: Array<Int?>, numVooReservas: Array<String?>, nomeReservas: Array<String?>, numVoo: Array<String?>, cidadePartida: Array<String?>, cidadeChegada: Array<String?>, diaVoo: Array<Int?>)
{
    println("Listagem de Passageiros:")
    println("Insira o voo desejado. Voos existentes: TP23 | FR2095 | TP1971 | VN453 | CRN221 | GH3 | S48425 | LH1172 | OS7395")
    var vooDesejado= readLine()!!
    while ((vooDesejado != "TP23") && (vooDesejado != "FR2095") && (vooDesejado != "TP1971") && (vooDesejado != "VN453") && (vooDesejado != "CRN221") && (vooDesejado != "GH3") && (vooDesejado != "S48425") && (vooDesejado != "LH1172") && (vooDesejado != "OS7395")){
        println("Insira um voo válido.")
        vooDesejado = readLine()!!
    }
    println("Insira o dia desejado.")
    var diaDesejado = readLine()!!.toInt()
    while ((diaDesejado<0) && (diaDesejado>30)) {
        println("Insira um dia válido.")
        diaDesejado = readLine()!!.toInt()
    }
    var infCorreta = false
    for(pos in 0..diaVoo.size-1) {
        if(diaVoo[pos]!= null) {
            if((numVoo[pos] == vooDesejado) && (diaVoo[pos] == diaDesejado)) {
                println("✫✫✫✫✫ \t « Já Fui » \t ✫✫✫✫✫")
                println("O voo do dia " + diaVoo[pos] + " , procedente de " + cidadePartida[pos] + " e com destino " + cidadeChegada[pos] + " terá como passageiros:")
                println("✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫\n")
                infCorreta = true
                break
            }
        }
    }
    if(infCorreta==true) {
        for (pos in 0..3) {
            if (numVooReservas[pos] != null) {
                if ((numVooReservas[pos] == vooDesejado) && (diaReservas[pos] == diaDesejado)) {
                    println(nomeReservas[pos])
                }
            }
        }
        println("\n✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫\n")
    } else {
        println("Não foi encontrado nenhum voo com a informação inserida, pedimos desculpa pelo incômodo.\n")
    }
}

fun numPassageirosXDias(numCC: Array<Int?>, nomeReservas: Array<String?>, numVooReservas: Array<String?>, diaReservas: Array<Int?>){
    println("Número de passageiros num período de tempo.")
    println("Insira o dia menor.")
    var diaMenor = readLine()!!.toInt()
    while ((diaMenor<1) || (diaMenor>30)){
        println("Insira um dia válido")
        diaMenor = readLine()!!.toInt()
    }
    println("Insira o dia maior.")
    var diaMaior = readLine()!!.toInt()
    while ((diaMaior<1)||(diaMaior>30)){
        println("Insira um dia válido")
        diaMaior = readLine()!!.toInt()
    }
    if (diaMenor>diaMaior){
        println("ERRO: O valor introduzido no dia menor é superior ao valor introduzido no dia maior.")
    }
    var passageirosPorDia = 0
    var posicao = 0
    val reservas = "reservas.txt"
    try {
        val lines = File(reservas).readLines()
        println("✫✫✫✫✫ \t « Já Fui » \t ✫✫✫✫✫")
        for (dias in diaMenor..diaMaior){
            passageirosPorDia= 0
            for (line in lines) {
                val parts = line.split(':')
                numCC[posicao] = parts[0].toInt()
                nomeReservas[posicao] = parts[1]
                numVooReservas[posicao] = parts[2]
                diaReservas[posicao] = parts[3].toInt()
                if(dias == diaReservas[posicao]){
                    passageirosPorDia++
                }
            }
            println("Dia $dias -> $passageirosPorDia passageiros")
        }
        println("✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫")
    } catch (e: FileNotFoundException) {
        println("Erro: $reservas não foi encontrado.")
    }
}

fun escritaEmFicheiro(numCC: Array<Int?>, nomeReservas: Array<String?>, numVooReservas: Array<String?>, diaReservas: Array<Int?>){
    println("Escrita em ficheiro:")
    val reservas = "reservas.txt"
    val writer = File(reservas).bufferedWriter()
    for(posicao in 0..numCC.size - 1){
        if ((numCC[posicao] == null) || (nomeReservas[posicao] == null) || (numVooReservas[posicao] == null) || (diaReservas[posicao] == null)){
            break
        }
        writer.write("${numCC[posicao]}:${nomeReservas[posicao]}:${numVooReservas[posicao]}:${diaReservas[posicao]}")
        writer.newLine()
        println("Gravei reserva para vôo ${numVooReservas[posicao]}")
    }
        writer.close()

}

fun analiseEconomica(numVoo : Array<String?>) {
    var auxiliarReservas = arrayOfNulls<String>(150)
    var intruso = false
    println("✫✫✫✫✫ \t « Já Fui » \t ✫✫✫✫✫")
    for (posicao in 0..numVoo.size - 1) {
        if (numVoo[posicao] != null) {
            if (posicao == 0) {
                auxiliarReservas[0] = numVoo[0]
            } else {
                for (posicao1 in 0..auxiliarReservas.size - 1) {
                    if (auxiliarReservas[posicao1] != null) {
                        if (numVoo[posicao] != auxiliarReservas[posicao1]) {
                            intruso = true
                        } else {
                            intruso = false
                            break
                        }
                    }
                }
                if (intruso == true) {
                    var pox = qtdRegistos(auxiliarReservas)
                    auxiliarReservas[pox] = numVoo[posicao]
                }
            }
        }
    }
    for ( posicao in 0..auxiliarReservas.size-1) {
        var resultado = ""
        if (auxiliarReservas[posicao] != null) {
            for (posicao1 in 0..numVoo.size - 1) {
                if (auxiliarReservas[posicao] == numVoo[posicao1]) {
                    resultado += "#"
                }

            }
            var qtdsespacos = 10-auxiliarReservas[posicao]!!.length
            var espacos = ""
            for (espacamento in 0..qtdsespacos) {
                espacos+= " "
            }
            println(auxiliarReservas[posicao] + espacos + resultado)
        }
    }
    println("✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫✫")
}

fun qtdRegistos (numVoo : Array<String?>): Int {
    var posicao = 0
    for(posicao2 in 0..numVoo.size-1) {
        if (numVoo[posicao2]==null)
        {
            posicao = posicao2
            break
        }
    }
    return posicao
}

fun reservaSimples(diaReservas: Array<Int?>, numVooReservas: Array<String?>, numCC: Array<Int?>, nomeReservas: Array<String?>, numVoo: Array<String?>, cidadePartida: Array<String?>, cidadeChegada: Array<String?>, diaVoo: Array<Int?>, idade: Array<Int?>) {
    if(numVooReservas[numVooReservas.size-1]!= null) {
        println("O ficheiro das reservas está lotado.")
    } else {
        println("Reserva simples de voo:\n")
        println("Qual a origem do voo? Destinos possíveis: Lisboa")
        var CidadePartida = readLine()!!
        while (CidadePartida != "Lisboa"){
            println("Insira uma origem válida")
            CidadePartida = readLine()!!
        }
        println("Qual o destino do voo? Destinos possíveis: Londres | Madrid | Roma | Milão | Paris | Frankfurt | Bucareste | Berlim | Amesterdão")
        var CidadeChegada = readLine()!!
        while ((CidadeChegada != "Londres") && (CidadeChegada != "Madrid") && (CidadeChegada != "Roma") && (CidadeChegada != "Milão") && (CidadeChegada != "Paris") && (CidadeChegada != "Fankfurt") && (CidadeChegada != "Bucareste") && (CidadeChegada != "Berlim") && (CidadeChegada != "Amesterdão")){
            println("Insira um destino válido")
            CidadeChegada = readLine()!!
        }
        println("Qual o dia pedido para o destino?")
        var DiaVoo = readLine()!!.toInt()
        val reservaPedida = arrayOfNulls<String>(20)
        var qtd_reservapedida = 0
        for (posicao in 0..numVoo.size - 1) {
            if (numVooReservas[posicao] != null) {
                if ((cidadePartida[posicao] == CidadePartida) && (cidadeChegada[posicao] == CidadeChegada) && (diaVoo[posicao] == DiaVoo)) {
                    reservaPedida[qtd_reservapedida] = numVoo[posicao]
                    qtd_reservapedida++
                }
            }
        }
        if (qtd_reservapedida >= 1) {
            println("Listagem de voos disponíveis:\n")
            var AuxReservas = arrayOfNulls<String>(20)
            var qtd_voos = 0
            var existevagas = true
            for (posicao in 0..reservaPedida.size - 1) {
                var qtdPassageirosVoos = ""
                if (reservaPedida[posicao] != null) {
                    for (posicaoreservas in 0..numVooReservas.size - 1) {
                        if ((numVooReservas[posicaoreservas] == reservaPedida[posicao]) && (diaReservas[posicaoreservas] == DiaVoo)) {
                            qtdPassageirosVoos += "#"
                        }
                    }
                    var qtd = 20 - qtdPassageirosVoos.length
                    AuxReservas[qtd_voos] = reservaPedida[posicao]
                    if(qtd==0) {
                        println("O voo " + reservaPedida[posicao] + " está lotado")
                        existevagas = false
                    } else {
                        println("O voo " + reservaPedida[posicao] + " tem " + qtd + " vagas disponíveis.")
                        existevagas = true
                    }
                }
            }
            if(existevagas==true) {
                var certo = false
                println("Indique o voo para reservar : ")
                while (certo == false) {
                    var vooResultado = readLine()
                    for (posicaoaux in 0..AuxReservas.size - 1) {
                        if (AuxReservas[posicaoaux] != null) {
                            if (vooResultado != AuxReservas[posicaoaux]) {
                                certo = false
                            } else {
                                certo = true
                                break
                            }
                        }
                    }
                    if (certo == false) {
                        println("Escreva um dos voos da lista.")
                    } else {
                        println("Indique o número de cartão de cidadão:")
                        var CC = readLine()!!.toInt()
                        println("Indique o nome:")
                        var nome = readLine()
                        println("Indique a idade:")
                        var idadePassageiro = readLine()!!.toInt()
                        var pos = qtdRegistos(numVooReservas)
                        numCC[pos] = CC
                        nomeReservas[pos] = nome
                        numVooReservas[pos] = vooResultado
                        diaReservas[pos] = DiaVoo
                        idade[pos] = idadePassageiro
                        println("Clique na opção 3 para guardar a sua reserva. Obrigado pela preferencia!\n")
                    }
                }
            }
        }
        else {
           for(pos in 0..numVoo.size-1) {
               if (numVooReservas[pos] != null) {
                   if(cidadePartida[pos] == CidadePartida && cidadeChegada[pos] == CidadeChegada) {
                      var dia : Int? = diaReservas[pos]
                      var diamenor : Int = dia!!-4
                      var diamaior  : Int = dia!!+4
                      if(dia > diamenor && dia < diamaior) {
                           println("Existe o voo "+numVoo[pos]+" no dia "+diaVoo[pos]+" é uma possibilidade.")
                      }
                   }
               }
           }
            println()
        }
        println()
    }
}

fun main(args: Array<String>) {
    val numVoo = arrayOfNulls<String>(20)
    val cidadePartida = arrayOfNulls<String>(20)
    val cidadeChegada = arrayOfNulls<String>(20)
    val diaVoo = arrayOfNulls<Int>(20)
    val numCC = arrayOfNulls<Int>(150)
    val nomeReservas = arrayOfNulls<String>(150)
    val numVooReservas = arrayOfNulls<String>(150)
    val diaReservas = arrayOfNulls<Int>(150)
    val idade = arrayOfNulls<Int>(150)
    lerReservas(numCC, nomeReservas, numVooReservas, diaReservas)
    lerVoos(numVoo, cidadePartida, cidadeChegada, diaVoo)
    do {
        menu()
        var opcao = readLine()!!.toIntOrNull()
        while (opcao == null) {
            println("Insira uma opção válida.")
            opcao = readLine()!!.toIntOrNull()
        }
        when (opcao) {
            0 -> sair()
            1 -> listarPassageiros(diaReservas, numVooReservas, nomeReservas, numVoo, cidadePartida, cidadeChegada, diaVoo)
            2 -> numPassageirosXDias(numCC, nomeReservas, numVooReservas, diaReservas)
            3 -> escritaEmFicheiro(numCC, nomeReservas, numVooReservas, diaReservas)
            4 -> analiseEconomica(numVooReservas)
            5 -> reservaSimples(diaReservas, numVooReservas, numCC, nomeReservas, numVoo, cidadePartida, cidadeChegada, diaVoo,idade)
            else -> println("Insira uma opção válida.")
        }
    } while (opcao != 0)
}

