postscript("TestingPrioritization.EPSILON.Boxplot.eps", horizontal=FALSE, onefile=FALSE, height=8, width=12, pointsize=10)
resultDirectory<-"../data/"
qIndicator <- function(indicator, problem)
{
filePGGA<-paste(resultDirectory, "PGGA", sep="/")
filePGGA<-paste(filePGGA, problem, sep="/")
filePGGA<-paste(filePGGA, indicator, sep="/")
PGGA<-scan(filePGGA)

algs<-c("PGGA")
boxplot(PGGA,names=algs, notch = FALSE)
titulo <-paste(indicator, problem, sep=":")
title(main=titulo)
}
par(mfrow=c(2,1))
indicator<-"EPSILON"
qIndicator(indicator, "TestingPrioritizationProblem")
