package oscar.com.eater.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import oscar.com.eater.Pojo.Instruction;

/**
 * Created by omenji on 3/29/17.
 */

public class AnalyzedInstructionsResponse  {
    @SerializedName("name")
    public String name;

    @SerializedName("steps")
    public List<Instruction> detailedInstructions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Instruction> getDetailedInstructions() {
        return detailedInstructions;
    }

    public void setDetailedInstructions(List<Instruction> detailedInstructions) {
        this.detailedInstructions = detailedInstructions;
    }
}
