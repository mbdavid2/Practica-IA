












#Comparación de los beneficios
boxplot(list("Normal"=normal$V1,"B2"=beneficiox2$V1,"B2Pe2"=B2Pe2$V1,"B2Pe2De2"=B2Pe2De2$V1), ylab="Beneficio (€)", main = "Beneficio")

summary(normal$V1)
summary(beneficiox2$V1)
summary(B2Pe2$V1)
summary(B2Pe2De2$V1)


boxplot(list("560"=menos$V1,"640"=medio$V1,"720"=mas$V1), ylab="Beneficio (€)", main = "Beneficio")

summary(menos$V1)
summary(medio$V1)
summary(mas$V1)

test <- t.test(menos$V1,medio$V1)
print(test)

