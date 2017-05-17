package oscar.com.eater.Observers;

import android.content.Context;
import android.widget.ListView;
import com.google.gson.Gson;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import oscar.com.eater.Adapter.RecipeInstructionsAdapter;
import oscar.com.eater.Interfaces.AbstractObserver;
import oscar.com.eater.Pojo.Instruction;
import oscar.com.eater.Response.AnalyzedInstructionsResponse;

/**
 * Created by omenji on 3/29/17.
 */

public class ObserverAnalyzedInstructions extends AbstractObserver implements Observer<String> {
    private ListView mInstructionTextView;
    private Context mContext;

    private ObserverAnalyzedInstructions(){

    }
    public static ObserverAnalyzedInstructions Build(){
        return new ObserverAnalyzedInstructions();
    }
    public ObserverAnalyzedInstructions withListView(ListView textView){
        mInstructionTextView = textView;
        return this;
    }

    public ObserverAnalyzedInstructions withContext(Context context){
        mContext = context;
        return this;
    }

    private void showInstructions(List<Instruction> instructionList){
        mInstructionTextView.setAdapter(new RecipeInstructionsAdapter(mContext,instructionList));
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String value) {
        Gson gson = new Gson();
        AnalyzedInstructionsResponse[] responseList = gson.fromJson(value,AnalyzedInstructionsResponse[].class);
        AnalyzedInstructionsResponse response = responseList[0];
        showInstructions(response.getDetailedInstructions());
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
