library('effsize')

base.directory <- "/Users/apanichella/Dropbox/DarioDiNucci/results/"

### Script for 2D ###
#reading bash 2d
bash <-read.csv(paste(base.directory, "bash/output_2D/results.csv", sep=""), header=T)
bash.greedy <- bash[which(bash$runName=="AdditionalGreedy"),]
bash.ga <- bash[which(bash$runName!="AdditionalGreedy"),]
bash.test <- t.test(bash.ga$AFDPC, matrix(bash.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
bash.effsize <-VD.A(bash.ga$AFDPC, matrix(bash.greedy$AFDPC,20,1))

#reading flex 2d
flex <-read.csv(paste(base.directory, "flex/output_2D/results.csv", sep=""), header=T)
flex.greedy <- flex[which(flex$runName=="AdditionalGreedy"),]
flex.ga <- flex[which(flex$runName!="AdditionalGreedy"),]
flex.test <- t.test(flex.ga$AFDPC, matrix(flex.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
flex.effsize <-VD.A(flex.ga$AFDPC, matrix(flex.greedy$AFDPC,20,1))

#reading grep 2d
grep <-read.csv(paste(base.directory, "grep/output_2D/results.csv", sep=""), header=T)
grep.greedy <- grep[which(grep$runName=="AdditionalGreedy"),]
grep.ga <- grep[which(grep$runName!="AdditionalGreedy"),]
grep.test <- t.test(grep.ga$AFDPC, matrix(grep.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
grep.effsize <-VD.A(grep.ga$AFDPC, matrix(grep.greedy$AFDPC,20,1))

#reading printtokens 2d
printtokens <-read.csv(paste(base.directory, "printtokens/output_2D/results.csv", sep=""), header=T)
printtokens.greedy <- printtokens[which(printtokens$runName=="AdditionalGreedy"),]
printtokens.ga <- printtokens[which(printtokens$runName!="AdditionalGreedy"),]
printtokens.test <- t.test(printtokens.ga$AFDPC, matrix(printtokens.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
printtokens.effsize <-VD.A(printtokens.ga$AFDPC, matrix(printtokens.greedy$AFDPC,20,1))

#reading printtokens2 2d
printtokens2 <-read.csv(paste(base.directory, "printtokens2/output_2D/results.csv", sep=""), header=T)
printtokens2.greedy <- printtokens2[which(printtokens2$runName=="AdditionalGreedy"),]
printtokens2.ga <- printtokens2[which(printtokens2$runName!="AdditionalGreedy"),]
printtokens2.test <- t.test(printtokens2.ga$AFDPC, matrix(printtokens2.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
printtokens2.effsize <-VD.A(printtokens2.ga$AFDPC, matrix(printtokens2.greedy$AFDPC,20,1))

#reading sed 2d
sed <-read.csv(paste(base.directory, "sed/output_2D/results.csv", sep=""), header=T)
sed.greedy <- sed[which(sed$runName=="AdditionalGreedy"),]
sed.ga <- sed[which(sed$runName!="AdditionalGreedy"),]
sed.test <- t.test(sed.ga$AFDPC, matrix(sed.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
sed.effsize <-VD.A(sed.ga$AFDPC, matrix(sed.greedy$AFDPC,20,1))


print("### Results for 2-objects ###")
print("# Mean APFDc #")

results.table <- matrix("",6,5)
colnames(results.table) <- c("System", "Add.Greedy mean", "GA mean", "GA standard deviation","difference")
results.table[1,] <- c("bash", round(mean(bash.greedy$AFDPC),digit=3), round(mean(bash.ga$AFDPC),digit=3), round(sd(bash.ga$AFDPC),digit=3), round(1-mean(bash.greedy$AFDPC)/mean(bash.ga$AFDPC),digit=3))
results.table[2,] <- c("flex", round(mean(flex.greedy$AFDPC),digit=3), round(mean(flex.ga$AFDPC),digit=3), round(sd(flex.ga$AFDPC),digit=3), round(1-mean(flex.greedy$AFDPC)/mean(flex.ga$AFDPC),digit=3))
results.table[3,] <- c("grep", round(mean(grep.greedy$AFDPC),digit=3), round(mean(grep.ga$AFDPC),digit=3), round(sd(grep.ga$AFDPC),digit=3), round(1-mean(grep.greedy$AFDPC)/mean(grep.ga$AFDPC),digit=3))
results.table[4,] <- c("printtokens", round(mean(printtokens.greedy$AFDPC),digit=3), round(mean(printtokens.ga$AFDPC),digit=3), round(sd(printtokens.ga$AFDPC),digit=3), round(1-mean(printtokens.greedy$AFDPC)/mean(printtokens.ga$AFDPC),digit=3))
results.table[5,] <- c("printtokens2", round(mean(printtokens2.greedy$AFDPC),digit=3), round( mean(printtokens2.ga$AFDPC),digit=3), round(sd(printtokens2.ga$AFDPC),digit=3), round(1-mean(printtokens2.greedy$AFDPC)/mean(printtokens2.ga$AFDPC),digit=3))
results.table[6,] <- c("sed", round(mean(sed.greedy$AFDPC),digit=3), round(mean(sed.ga$AFDPC),digit=3), round(sd(sed.ga$AFDPC),digit=3), round(1-mean(sed.greedy$AFDPC)/mean(sed.ga$AFDPC),digit=3))
print(as.data.frame(results.table))

print("");
print("# Results of Welch's t test #")
print("");
results.test <- matrix("",6,2)
colnames(results.test) <- c("System", "p.values")
results.test[1,] <- c("bash", bash.test$p.value)
results.test[2,] <- c("flex", flex.test$p.value)
results.test[3,] <- c("grep", grep.test$p.value)
results.test[4,] <- c("printtokens", printtokens.test$p.value)
results.test[5,] <- c("printtokens2", printtokens2.test$p.value)
results.test[6,] <- c("sed", sed.test$p.value)
names(results.table) <- c("System", "Welch's t test")
print(as.data.frame(results.test))

print("");
print("# Vargha Delaney Statistics (Effect Size) #")
print("");
results.effsize <- matrix("",6,3)
colnames(results.effsize) <- c("System", "Magnitude", "Label")
results.effsize[1,] <- c("bash", bash.effsize$estimate, bash.effsize$magnitude)
results.effsize[2,] <- c("flex", flex.effsize$estimate, flex.effsize$magnitude)
results.effsize[3,] <- c("grep", grep.effsize$estimate, grep.effsize$magnitude)
results.effsize[4,] <- c("printtokens", printtokens.effsize$estimate, printtokens.effsize$magnitude)
results.effsize[5,] <- c("printtokens2", printtokens2.effsize$estimate, printtokens2.effsize$magnitude)
results.effsize[6,] <- c("sed", sed.effsize$estimate, sed.effsize$magnitude)
names(results.table) <- c("System", "Magnitude", "Label")
print(as.data.frame(results.effsize))

### Script for 3D ###
#reading bash 3d
bash <-read.csv(paste(base.directory, "bash/output_3D/results.csv", sep=""), header=T)
bash.greedy <- bash[which(bash$runName=="AdditionalGreedy"),]
bash.ga <- bash[which(bash$runName!="AdditionalGreedy"),]
bash.test <- t.test(bash.ga$AFDPC, matrix(bash.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
bash.effsize <-VD.A(bash.ga$AFDPC, matrix(bash.greedy$AFDPC,20,1))

#reading flex 3d
flex <-read.csv(paste(base.directory, "flex/output_3D/results.csv", sep=""), header=T)
flex.greedy <- flex[which(flex$runName=="AdditionalGreedy"),]
flex.ga <- flex[which(flex$runName!="AdditionalGreedy"),]
flex.test <- t.test(flex.ga$AFDPC, matrix(flex.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
flex.effsize <-VD.A(flex.ga$AFDPC, matrix(flex.greedy$AFDPC,20,1))

#reading grep 3d
grep <-read.csv(paste(base.directory, "grep/output_3D/results.csv", sep=""), header=T)
grep.greedy <- grep[which(grep$runName=="AdditionalGreedy"),]
grep.ga <- grep[which(grep$runName!="AdditionalGreedy"),]
grep.test <- t.test(grep.ga$AFDPC, matrix(grep.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
grep.effsize <-VD.A(grep.ga$AFDPC, matrix(grep.greedy$AFDPC,20,1))

#reading printtokens 3d
printtokens <-read.csv(paste(base.directory, "printtokens/output_3D/results.csv", sep=""), header=T)
printtokens.greedy <- printtokens[which(printtokens$runName=="AdditionalGreedy"),]
printtokens.ga <- printtokens[which(printtokens$runName!="AdditionalGreedy"),]
printtokens.test <- t.test(printtokens.ga$AFDPC, matrix(printtokens.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
printtokens.effsize <-VD.A(printtokens.ga$AFDPC, matrix(printtokens.greedy$AFDPC,20,1))

#reading printtokens2 3d
printtokens2 <-read.csv(paste(base.directory, "printtokens2/output_3D/results.csv", sep=""), header=T)
printtokens2.greedy <- printtokens2[which(printtokens2$runName=="AdditionalGreedy"),]
printtokens2.ga <- printtokens2[which(printtokens2$runName!="AdditionalGreedy"),]
printtokens2.test <- t.test(printtokens2.ga$AFDPC, matrix(printtokens2.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
printtokens2.effsize <-VD.A(printtokens2.ga$AFDPC, matrix(printtokens2.greedy$AFDPC,20,1))

#reading sed 3d
sed <-read.csv(paste(base.directory, "sed/output_3D/results.csv", sep=""), header=T)
sed.greedy <- sed[which(sed$runName=="AdditionalGreedy"),]
sed.ga <- sed[which(sed$runName!="AdditionalGreedy"),]
sed.test <- t.test(sed.ga$AFDPC, matrix(sed.greedy$AFDPC,20,1), alternative="greater", var.equal=F)
sed.effsize <-VD.A(sed.ga$AFDPC, matrix(sed.greedy$AFDPC,20,1))

print("");
print("### Results for 3-objects ###")
print("");
print("# Mean APFDc #")
results.table <- matrix("",6,5)
colnames(results.table) <- c("System", "Add.Greedy mean", "GA mean", "GA standard deviation","difference")
results.table[1,] <- c("bash", round(mean(bash.greedy$AFDPC),digit=3), round(mean(bash.ga$AFDPC),digit=3), round(sd(bash.ga$AFDPC),digit=3), round(1-mean(bash.greedy$AFDPC)/mean(bash.ga$AFDPC),digit=3))
results.table[2,] <- c("flex", round(mean(flex.greedy$AFDPC),digit=3), round(mean(flex.ga$AFDPC),digit=3), round(sd(flex.ga$AFDPC),digit=3), round(1-mean(flex.greedy$AFDPC)/mean(flex.ga$AFDPC),digit=3))
results.table[3,] <- c("grep", round(mean(grep.greedy$AFDPC),digit=3), round(mean(grep.ga$AFDPC),digit=3), round(sd(grep.ga$AFDPC),digit=3), round(1-mean(grep.greedy$AFDPC)/mean(grep.ga$AFDPC),digit=3))
results.table[4,] <- c("printtokens", round(mean(printtokens.greedy$AFDPC),digit=3), round(mean(printtokens.ga$AFDPC),digit=3), round(sd(printtokens.ga$AFDPC),digit=3), round(1-mean(printtokens.greedy$AFDPC)/mean(printtokens.ga$AFDPC),digit=3))
results.table[5,] <- c("printtokens2", round(mean(printtokens2.greedy$AFDPC),digit=3), round( mean(printtokens2.ga$AFDPC),digit=3), round(sd(printtokens2.ga$AFDPC),digit=3), round(1-mean(printtokens2.greedy$AFDPC)/mean(printtokens2.ga$AFDPC),digit=3))
results.table[6,] <- c("sed", round(mean(sed.greedy$AFDPC),digit=3), round(mean(sed.ga$AFDPC),digit=3), round(sd(sed.ga$AFDPC),digit=3), round(1-mean(sed.greedy$AFDPC)/mean(sed.ga$AFDPC),digit=3))
print(as.data.frame(results.table))

print("");
print("# Results of Welch's t test #")
print("");
results.test <- matrix("",6,2)
colnames(results.test) <- c("System", "p.values")
results.test[1,] <- c("bash", bash.test$p.value)
results.test[2,] <- c("flex", flex.test$p.value)
results.test[3,] <- c("grep", grep.test$p.value)
results.test[4,] <- c("printtokens", printtokens.test$p.value)
results.test[5,] <- c("printtokens2", printtokens2.test$p.value)
results.test[6,] <- c("sed", sed.test$p.value)
names(results.table) <- c("System", "Welch's t test")
print(as.data.frame(results.test))

print("");
print("# Vargha Delaney Statistics (Effect Size) #")
print("");
results.effsize <- matrix("",6,3)
colnames(results.effsize) <- c("System", "Magnitude", "Label")
results.effsize[1,] <- c("bash", bash.effsize$estimate, bash.effsize$magnitude)
results.effsize[2,] <- c("flex", flex.effsize$estimate, flex.effsize$magnitude)
results.effsize[3,] <- c("grep", grep.effsize$estimate, grep.effsize$magnitude)
results.effsize[4,] <- c("printtokens", printtokens.effsize$estimate, printtokens.effsize$magnitude)
results.effsize[5,] <- c("printtokens2", printtokens2.effsize$estimate, printtokens2.effsize$magnitude)
results.effsize[6,] <- c("sed", sed.effsize$estimate, sed.effsize$magnitude)
names(results.table) <- c("System", "Magnitude", "Label")
print(as.data.frame(results.effsize))