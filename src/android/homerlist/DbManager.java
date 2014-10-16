package android.homerlist;

import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.model.system.User;
import com.telerik.everlive.sdk.core.query.definition.FileField;
import com.telerik.everlive.sdk.core.query.definition.UserSecretInfo;
import com.telerik.everlive.sdk.core.query.definition.filtering.Condition;
import com.telerik.everlive.sdk.core.query.definition.filtering.array.ArrayCondition;
import com.telerik.everlive.sdk.core.query.definition.filtering.array.ArrayConditionOperator;
import com.telerik.everlive.sdk.core.query.definition.filtering.compound.CompoundCondition;
import com.telerik.everlive.sdk.core.query.definition.filtering.compound.CompoundConditionOperator;
import com.telerik.everlive.sdk.core.query.definition.filtering.simple.ValueCondition;
import com.telerik.everlive.sdk.core.query.definition.filtering.simple.ValueConditionOperator;
import com.telerik.everlive.sdk.core.query.definition.sorting.SortDirection;
import com.telerik.everlive.sdk.core.query.definition.sorting.SortingDefinition;
import com.telerik.everlive.sdk.core.result.RequestResultCallbackAction;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbManager {
    private static DbManager instance;

    private EverliveApp everlive;

    private SecureRandom random = new SecureRandom();

    private DbManager() {
    }

    public static DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    public void setEverlive(String apiKey) {
        this.everlive = new EverliveApp(apiKey);
    }

    public void getMe(RequestResultCallbackAction callbackAction) {
        this.everlive.
                workWith().
                users().
                getMe().
                executeAsync(callbackAction);
    }  

    public void register(User user, UserSecretInfo secretInfo, RequestResultCallbackAction callbackAction) {
        this.everlive.workWith().
                users().
                create(user, secretInfo).
                executeAsync(callbackAction);
    }

    public void login(String userName, String password, RequestResultCallbackAction callbackAction) {
        this.everlive.workWith().
                authentication().
                login(userName, password).
                executeAsync(callbackAction);
    }

    public void changePassword(String username, String currentPassword, String newPassword){
        this.everlive.workWith().users().changePassword(username, currentPassword, newPassword).executeAsync();
    }

    public void createGoal(Goal goal, RequestResultCallbackAction callbackAction) {
        this.everlive.workWith().
                data(Goal.class).
                create(goal).
                executeAsync(callbackAction);
    }

    public void deleteGoals(RequestResultCallbackAction callbackAction) {
        List<String> goalIdsToBeDeleted = new ArrayList<String>();
        List<Goal> goalsToBeDeleted = new ArrayList<Goal>();

//        for (Goal goal : LoggedUser.getInstance().getGoals()) {
//            if (goal.isSelected()) {
//                goalIdsToBeDeleted.add(goal.getId().toString());
//                goalsToBeDeleted.add(goal);
//            }
//        }
//
//        for (Goal goal : goalsToBeDeleted) {
//            LoggedUser.getInstance().getGoals().remove(goal);
//        }

        Condition containsCondition = new ArrayCondition("Id", ArrayConditionOperator.ValueIsIn, goalIdsToBeDeleted);

        this.everlive.workWith().
                data(Goal.class).
                delete().
                where(containsCondition).
                executeAsync(callbackAction);
    }

    public void updateGoalToCompleted(Goal goal, RequestResultCallbackAction callbackAction) {
        Goal updatedGoal = new Goal();
        updatedGoal.setDone(goal.isDone());

        this.everlive.
                workWith().
                data(Goal.class).
                updateById(goal.getId(), updatedGoal).
                executeAsync(callbackAction);
    }

    public void updateGoalCover(Goal goal, RequestResultCallbackAction callbackAction) {
        Goal updatedGoal = new Goal();
        updatedGoal.setCover(goal.getCover());

        this.everlive.
                workWith().
                data(Goal.class).
                updateById(goal.getId(), updatedGoal).
                executeAsync(callbackAction);
    }

    public void uploadImage(InputStream inputStream, RequestResultCallbackAction callbackAction) {
        String fileName = new BigInteger(130, random).toString(32) + ".jpg";
        String contentType = "image/jpeg";

        FileField fileField = new FileField(fileName, contentType, inputStream);
        this.everlive.workWith().files().upload(fileField).executeAsync(callbackAction);
    }

    public void deleteImageById(String id) {
        this.everlive.workWith().
                files().
                deleteById(id).
                executeAsync();
    }

    public void downloadPicture(String pictureId, RequestResultCallbackAction callbackAction) {
        this.everlive.workWith().
                files().
                getById(pictureId).
                executeAsync(callbackAction);
    }
}