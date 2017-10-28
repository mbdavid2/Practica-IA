#Comparación de los beneficios estado inicial vacío
boxplot(list("vA"=V_A$Benef,"vAS1"=V_AS1$Benef,"vAS2"=V_AS2$Benef,"vAS1S2"=V_AS1S2$Benef), ylab="Beneficio (€)", xlab="Operadores", main = "Beneficio (estado inicial vacío)")

#Comparación de los tiempos estado inicial vacío
boxplot(list("vA"=V_A$Tiempo,"vAS1"=V_AS1$Tiempo,"vAS2"=V_AS2$Tiempo,"vAS1S2"=V_AS1S2$Tiempo), ylab="Tiempo (ms)", xlab="Operadores", main = "Tiempo (estado inicial vacío)")

#Comparación de los beneficios estado inicial lleno
boxplot(list("LA"=L_A$Benef,"LAS1"=L_AS1$Benef,"LAS2"=L_AS2$Benef,"LAS1S2"=L_AS1S2$Benef,"LS1"=L_S1$Benef,"LS2"=L_S2$Benef,"LS1S2"=L_S1S2$Benef), ylab="Beneficio (€)", xlab="Operadores", main = "Beneficio (estado inicial lleno)")

#Comparación de los tiempos estado inicial lleno
boxplot(list("LA"=L_A$Tiempo,"LAS1"=L_AS1$Tiempo,"LAS2"=L_AS2$Tiempo,"LAS1S2"=L_AS1S2$Tiempo,"LS1"=L_S1$Tiempo,"LS2"=L_S2$Tiempo,"LS1S2"=L_S1S2$Tiempo), ylab="Tiempo (ms)", xlab="Operadores", main = "Tiempo (estado inicial lleno)")

