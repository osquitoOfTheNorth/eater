package oscar.com.eater.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by omenji on 3/16/17.
 */

public class Instruction implements Serializable {
    @SerializedName("number")
    public int stepNumber;
    @SerializedName("step")
    public String instructionDetail;

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getInstructionDetail() {
        return instructionDetail;
    }

    public void setInstructionDetail(String instructionDetail) {
        this.instructionDetail = instructionDetail;
    }
}
