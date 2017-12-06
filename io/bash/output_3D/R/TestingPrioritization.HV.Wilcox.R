write("", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex",append=FALSE)
resultDirectory<-"/home/sesa/jMetal/io/bash/output_3D//data"
latexHeader <- function() {
  write("\\documentclass{article}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\title{StandardStudy}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\usepackage{amssymb}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\author{A.J.Nebro}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\begin{document}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\maketitle", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\section{Tables}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
}

latexTableHeader <- function(problem, tabularString, latexTableFirstLine) {
  write("\\begin{table}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\caption{", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write(problem, "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write(".HV.}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)

  write("\\label{Table:", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write(problem, "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write(".HV.}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)

  write("\\centering", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\begin{scriptsize}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\begin{tabular}{", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write(tabularString, "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write(latexTableFirstLine, "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\hline ", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
}

latexTableTail <- function() { 
  write("\\hline", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\end{tabular}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\end{scriptsize}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  write("\\end{table}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
}

latexTail <- function() { 
  write("\\end{document}", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
}

printTableLine <- function(indicator, algorithm1, algorithm2, i, j, problem) { 
  file1<-paste(resultDirectory, algorithm1, sep="/")
  file1<-paste(file1, problem, sep="/")
  file1<-paste(file1, indicator, sep="/")
  data1<-scan(file1)
  file2<-paste(resultDirectory, algorithm2, sep="/")
  file2<-paste(file2, problem, sep="/")
  file2<-paste(file2, indicator, sep="/")
  data2<-scan(file2)
  if (i == j) {
    write("--", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  }
  else if (i < j) {
    if (wilcox.test(data1, data2)$p.value <= 0.05) {
      if (median(data1) >= median(data2)) {
        write("$\\blacktriangle$", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
      }
      else {
        write("$\\triangledown$", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE) 
      }
    }
    else {
      write("--", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE) 
    }
  }
  else {
    write(" ", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
  }
}

### START OF SCRIPT 
# Constants
problemList <-c("TestingPrioritizationProblemWFaults") 
algorithmList <-c("PGGA") 
tabularString <-c("l") 
latexTableFirstLine <-c("\\hline \\\\ ") 
indicator<-"HV"

 # Step 1.  Writes the latex header
latexHeader()
# Step 2. Problem loop 
for (problem in problemList) {
  latexTableHeader(problem,  tabularString, latexTableFirstLine)

  indx = 0
  for (i in algorithmList) {
    if (i != "PGGA") {
      write(i , "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
      write(" & ", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
      jndx = 0 
      for (j in algorithmList) {
        if (jndx != 0) {
          if (indx != jndx) {
            printTableLine(indicator, i, j, indx, jndx, problem)
          }
          else {
            write("  ", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
          }
          if (j != "PGGA") {
            write(" & ", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
          }
          else {
            write(" \\\\ ", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
          }
        }
        jndx = jndx + 1
      }
      indx = indx + 1
    }
  }

  latexTableTail()
} # for problem

tabularString <-c("| l | ") 

latexTableFirstLine <-c("\\hline \\multicolumn{1}{|c|}{} \\\\") 

# Step 3. Problem loop 
latexTableHeader("TestingPrioritizationProblemWFaults ", tabularString, latexTableFirstLine)

indx = 0
for (i in algorithmList) {
  if (i != "PGGA") {
    write(i , "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
    write(" & ", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)

    jndx = 0
    for (j in algorithmList) {
      for (problem in problemList) {
        if (jndx != 0) {
          if (i != j) {
            printTableLine(indicator, i, j, indx, jndx, problem)
          }
          else {
            write("  ", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
          } 
          if (problem == "TestingPrioritizationProblemWFaults") {
            if (j == "PGGA") {
              write(" \\\\ ", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
            } 
            else {
              write(" & ", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
            }
          }
     else {
    write("&", "/home/sesa/jMetal/io/bash/output_3D//R/TestingPrioritization.HV.Wilcox.tex", append=TRUE)
     }
        }
      }
      jndx = jndx + 1
    }
    indx = indx + 1
  }
} # for algorithm

  latexTableTail()

#Step 3. Writes the end of latex file 
latexTail()

