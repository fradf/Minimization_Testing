package it.unisa.prioritization.criterion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;

/**
 * This class encapsulate the execution cost of test cases
 *
 * @author Annibale Panichella
 */
public class ExecutionCostVector {

    public DoubleMatrix1D costArray_;

    public ExecutionCostVector(String costFilename) {
        try {
            costArray_ = readCostData(costFilename);
        } catch (IOException e) {
            Logger.getLogger(ExecutionCostVector.class.getName())
                    .log(Level.SEVERE, "Problem when creating the cost "
                            + "vector for the file " + costFilename, e);
        }
    }

    public double getMaxCost() {
        return this.costArray_.zSum();
    }

    public double getCostOfTest(int index) {
        return costArray_.get(index);
    }

    public int size() {
        return costArray_.size();
    }

    protected final DoubleMatrix1D readCostData(String costFilename) throws IOException {
        if (costFilename == null) {
            throw new IllegalArgumentException();
        }

        BufferedReader costBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(costFilename)));
        int row = 0;
        String line;
        while ((line = costBufferedReader.readLine()) != null) {
            row++;
        }
        costBufferedReader.close();

        double[] costArray = new double[row];

        costBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(costFilename)));

        row = 0;
        while ((line = costBufferedReader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, "");

            while (st.hasMoreTokens()) {
                costArray[row] = Double.valueOf(st.nextToken()).longValue();
            }
            row++;
        }

        DenseDoubleMatrix1D denseConstArray = new DenseDoubleMatrix1D(costArray);
        return denseConstArray;
    }

}
