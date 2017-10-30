








plot(SA_tiempo$V1, SA_tiempo$V2, type="o", xlab="Número de CDs", ylab="Tiempo (ms)", main = "Tiempo en función del número de centros")

plot(SA_tiempo2$V1, SA_tiempo2$V2, type="o", xlab="Número de CDs", ylab="Tiempo (ms)", main = "Tiempo en función del número de centros")


boxplot(list("560"=menos$V1,"640"=medio$V1,"720"=mas$V1), ylab="Beneficio (€)", main = "Beneficio")

summary(menos$V1)
summary(medio$V1)
summary(mas$V1)

test <- t.test(menos$V1,medio$V1)
print(test)

