write("", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex",append=FALSE)
resultDirectory<-"D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/data"
latexHeader <- function() {
  write("\\documentclass{article}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\title{StandardStudy}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\usepackage{amssymb}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\author{A.J.Nebro}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\begin{document}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\maketitle", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\section{Tables}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
}

latexTableHeader <- function(problem, tabularString, latexTableFirstLine) {
  write("\\begin{table}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\caption{", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write(problem, "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write(".EPSILON.}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)

  write("\\label{Table:", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write(problem, "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write(".EPSILON.}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)

  write("\\centering", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\begin{scriptsize}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\begin{tabular}{", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write(tabularString, "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write(latexTableFirstLine, "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\hline ", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
}

latexTableTail <- function() { 
  write("\\hline", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\end{tabular}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\end{scriptsize}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  write("\\end{table}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
}

latexTail <- function() { 
  write("\\end{document}", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
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
    write("-- ", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  }
  else if (i < j) {
    if (wilcox.test(data1, data2)$p.value <= 0.05) {
      if (median(data1) <= median(data2)) {
        write("$\\blacktriangle$", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
      }
      else {
        write("$\\triangledown$", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE) 
      }
    }
    else {
      write("--", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE) 
    }
  }
  else {
    write(" ", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
  }
}

### START OF SCRIPT 
# Constants
problemList <-c("TestingPrioritizationProblemWFaults") 
algorithmList <-c("PGGA") 
tabularString <-c("l") 
latexTableFirstLine <-c("\\hline \\\\ ") 
indicator<-"EPSILON"

 # Step 1.  Writes the latex header
latexHeader()
# Step 2. Problem loop 
for (problem in problemList) {
  latexTableHeader(problem,  tabularString, latexTableFirstLine)

  indx = 0
  for (i in algorithmList) {
    if (i != "PGGA") {
      write(i , "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
      write(" & ", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
      jndx = 0 
      for (j in algorithmList) {
        if (jndx != 0) {
          if (indx != jndx) {
            printTableLine(indicator, i, j, indx, jndx, problem)
          }
          else {
            write("  ", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
          }
          if (j != "PGGA") {
            write(" & ", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
          }
          else {
            write(" \\\\ ", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
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
    write(i , "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
    write(" & ", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)

    jndx = 0
    for (j in algorithmList) {
      for (problem in problemList) {
        if (jndx != 0) {
          if (i != j) {
            printTableLine(indicator, i, j, indx, jndx, problem)
          }
          else {
            write("  ", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
          } 
          if (problem == "TestingPrioritizationProblemWFaults") {
            if (j == "PGGA") {
              write(" \\\\ ", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
            } 
            else {
              write(" & ", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
            }
          }
     else {
    write("&", "D:\Documents\Unisa - phd\Netbeans workspace\jMetal\io\printtokens2\output_3D\/R/TestingPrioritization.EPSILON.Wilcox.tex", append=TRUE)
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

