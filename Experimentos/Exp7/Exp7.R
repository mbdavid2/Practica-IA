#Comparación de los beneficios
boxplot(list("5 CDs 2 camiones"=doscada$Benef,"10 CDs 1 camión"=unocada$Benef), ylab="Beneficio (€)", main = "Beneficio")

boxplot(list("5 CDs 2 camiones"=doscada$Km,"10 CDs 1 camión"=unocada$Km), ylab="Kilometros", main = "Distancia total recorrida")

summary(doscada$Km)
summary(unocada$Km)



######
#del exp1-2 por si se necesita algo

#Comparación de los tiempos estado inicial vacío
boxplot(list("vA"=V_A$Tiempo,"vAS1"=V_AS1$Tiempo,"vAS2"=V_AS2$Tiempo,"vAS1S2"=V_AS1S2$Tiempo), ylab="Tiempo (ms)", xlab="Operadores", main = "Tiempo (estado inicial vacío)")

#Comparación de los beneficios estado inicial lleno
boxplot(list("LA"=L_A$Benef,"LAS1"=L_AS1$Benef,"LAS2"=L_AS2$Benef,"LAS1S2"=L_AS1S2$Benef,"LS1"=L_S1$Benef,"LS2"=L_S2$Benef,"LS1S2"=L_S1S2$Benef), ylab="Beneficio (€)", xlab="Operadores", main = "Beneficio (estado inicial lleno)")

#Comparación de los tiempos estado inicial lleno
boxplot(list("LA"=L_A$Tiempo,"LAS1"=L_AS1$Tiempo,"LAS2"=L_AS2$Tiempo,"LAS1S2"=L_AS1S2$Tiempo,"LS1"=L_S1$Tiempo,"LS2"=L_S2$Tiempo,"LS1S2"=L_S1S2$Tiempo), ylab="Tiempo (ms)", xlab="Operadores", main = "Tiempo (estado inicial lleno)")



#Todos juntos
boxplot(list("vA"=V_A$Tiempo,"vAS1"=V_AS1$Tiempo,"vAS2"=V_AS2$Tiempo,"vAS1S2"=V_AS1S2$Tiempo,"LA"=L_A$Tiempo,"LAS1"=L_AS1$Tiempo,"LAS2"=L_AS2$Tiempo,"LAS1S2"=L_AS1S2$Tiempo,"LS1"=L_S1$Tiempo,"LS2"=L_S2$Tiempo,"LS1S2"=L_S1S2$Tiempo), ylab="Tiempo (ms)", xlab="Operadores", main = "Tiempo (estado inicial lleno)")

boxplot(list("vA"=V_A$Benef,"vAS1"=V_AS1$Benef,"vAS2"=V_AS2$Benef,"vAS1S2"=V_AS1S2$Benef, "LA"=L_A$Benef,"LAS1"=L_AS1$Benef,"LAS2"=L_AS2$Benef,"LAS1S2"=L_AS1S2$Benef,"LS1"=L_S1$Benef,"LS2"=L_S2$Benef,"LS1S2"=L_S1S2$Benef), ylab="Beneficio (€)", xlab="Operadores", main = "Beneficio (estado inicial vacío)")

test <- t.test(V_AS1$Benef, V_AS1S2$Benef)
print(test)

test <- t.test(V_AS1$Tiempo, V_AS1S2$Tiempo)
print(test)

summary(L_A$Benef)
summary(L_AS1$Benef)
summary(L_AS2$Benef)
summary(L_AS1S2$Benef)
summary(L_S1$Benef)
summary(L_S2$Benef)
summary(L_S1S2$Benef)

summary(L_A$Tiempo)
summary(L_AS1$Tiempo)
summary(L_AS2$Tiempo)
summary(L_AS1S2$Tiempo)
summary(L_S1$Tiempo)
summary(L_S2$Tiempo)
summary(L_S1S2$Tiempo)

summary(V_A$Tiempo)
summary(V_AS1$Tiempo)
summary(V_AS2$Tiempo)
summary(V_AS1S2$Tiempo)

summary(V_A$Benef)
summary(V_AS1$Benef)
summary(V_AS2$Benef)
summary(V_AS1S2$Benef)


#Comparación de los beneficios estado inicial lleno sin LA, LS1, LS2 ni LS1S2
boxplot(list("LAS1"=L_AS1$Benef,"LAS2"=L_AS2$Benef,"LAS1S2"=L_AS1S2$Benef), ylab="Beneficio (€)", xlab="Operadores", main = "Beneficio (estado inicial lleno)")

#Comparación de los tiempos estado inicial lleno sin LA, LS1, LS2 ni LS1S2
boxplot(list("LAS1"=L_AS1$Tiempo,"LAS2"=L_AS2$Tiempo,"LAS1S2"=L_AS1S2$Tiempo), ylab="Tiempo (ms)", xlab="Operadores", main = "Tiempo (estado inicial lleno)")


#Final, los mejores
boxplot(list("vAS1"=V_AS1$Benef,"LAS1"=L_AS1$Benef,"LAS2"=L_AS2$Benef), ylab="Beneficio (€)", xlab="Operadores", main = "Beneficio")

boxplot(list("vAS1"=V_AS1$Tiempo,"LAS1"=L_AS1$Tiempo,"LAS2"=L_AS2$Tiempo), ylab="Tiempo (ms)", xlab="Operadores", main = "Tiempo")

