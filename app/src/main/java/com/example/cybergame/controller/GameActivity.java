package com.example.cybergame.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import com.example.cybergame.R;
import com.example.cybergame.model.Question;
import com.example.cybergame.model.QuestionBank;


import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mGameQuestionTextView;
    private Button mGameButton1;
    private Button mGameButton2;
    private Button mGameButton3;
    private Button mGameButton4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestions;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";
    public static final String CORRECTNESS = "correctness";
    public static final String EXPLANATION = "explanation";

    private boolean mEnableTouchEvents;
    public static final int GAME_RETURN_CODE = MainActivity.GAME_RETURN_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity::onCreate()");

        mQuestionBank = this.generateQuestions();

        if(savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mNumberOfQuestions = 8;
        }

        mEnableTouchEvents = true;

        mGameQuestionTextView = findViewById(R.id.gameQuestionText);
        mGameButton1 = findViewById(R.id.gameAnswer1btn);
        mGameButton2 = findViewById(R.id.gameAnswer2btn);
        mGameButton3 = findViewById(R.id.gameAnswer3btn);
        mGameButton4 = findViewById(R.id.gameAnswer4btn);

        mGameButton1.setTag(0);
        mGameButton2.setTag(1);
        mGameButton3.setTag(2);
        mGameButton4.setTag(3);

        mGameButton1.setOnClickListener(GameActivity.this);
        mGameButton2.setOnClickListener(GameActivity.this);
        mGameButton3.setOnClickListener(GameActivity.this);
        mGameButton4.setOnClickListener(GameActivity.this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);
        super.onSaveInstanceState(outState);
    }

    private void displayQuestion(final Question question) {
        mGameQuestionTextView.setText(question.getQuestion());
        mGameButton1.setText(question.getChoiceList().get(0));
        mGameButton2.setText(question.getChoiceList().get(1));
        mGameButton3.setText(question.getChoiceList().get(2));
        mGameButton4.setText(question.getChoiceList().get(3));
    }

    public String getName() {
        return getIntent().getStringExtra("name");
    }

    private QuestionBank generateQuestions() {
        Question question1 = new Question("Your boss wants the team to understand what kind of threats you may face whilst working at MediCo." +
                "\n\nHe asks you, what is the biggest type of cyber security vulnerability?",
                    Arrays.asList("Network vulnerabilities", "Operating System vulnerabilities", "Human vulnerabilities", "Process vulnerabilities"), 2,
                "Well done! Although it may be difficult to hear, it turns out that we humans are the biggest vulnerability " +
                        "when it comes to cyber security!! It makes sense, as it can be quite easy to fall for phishing scams and social engineering attacks, for example." +
                        " Of course, it is humans themselves who create these scams, so they know how people work…",
                "It's actually humans that are the biggest vulnerability, often causing more damage (including financially) than any other vulnerability type." +
                        " It can be very easy for us humans to fall victim to social engineering attacks, as it is humans themselves who create these attacks. Other vulnerabilities may " +
                        "be mitigated by more intense security measures, it's almost impossible to eliminate human error. We need to be alert!");

        Question question2 = new Question("You're given a tour of the manufacturing plant, but you see that an engineer has left their password written on a note," +
                " stuck the the machine that the password is for.\n\n" +
                "Firstly, they should not have left their password so easily accessible, especially for such an important machine." +
                "\nBut secondly, it's not a very secure password: 'popcorn55'. " +
                "\n\nHow long would it take this password to be cracked?", Arrays.asList("42 minutes", "2 minutes", "13 hours", "3 days"), 0,
                "Yep! According to \n'www.security.org/how-secure-is-my-password/',\nit would take just 42 minutes." +
                        " Of course, this all depends on the capability of the computer being used to crack it.\n\n" +
                        "It would be very easy to add a little more to it. For example, 'popcorn55octopus*' would take 84 billion years to crack!" +
                        " It’s definitely worth adding that little bit more to your password.",
                "Not quite! According to \n'www.security.org/how-secure-is-my-password/',\nit would take just 42 minutes." +
                        " Of course, this all depends on the capability of the computer being used to crack it.\n\n" +
                        "It would be very easy to add a little more to it. For example, 'popcorn55octopus*' would take 84 billion years to crack!" +
                        " It’s definitely worth adding that little bit more to your password.");

        Question question3 = new Question("An angry staff member thinks that he can make more money by stealing from the company than working for them." +
                " You overhear him explaining his plan to install a USB stick in a company computer.\n\n" +
                "What needs to be implemented to prevent this employee from stealing data?\n", Arrays.asList("Encryption", "Network monitoring", "CCTV", "Firewall"), 0,
                "'Prevent' is the key word here. The other security features don’t prevent this method of data theft, but they could certainly be helpful.\n\n" +
                        "There are so many types of encryption - have a look at CyberChef, an online tool by GCHQ, where you have experiment with different types of encryption.",
                "'Prevent' is the key word here. Encryption helps to prevent unauthorised data access.\n\n" +
                        "Network monitoring - although this may help identify the culprit by tracking network behaviour, it doesn't actually stop the theft in the first place.\n\n" +
                        "CCTV - like network monitoring, it may help identify the culprit afterwards, but won't prevent it.\n\n" +
                        "Firewall - this monitors incoming and outgoing traffic on the network. It's definitely useful, but not relevant to this situation.");

        Question question4 = new Question("You log into your computer, and you've received the following email:\n\n" +
                "FROM: medico84@protonmail.com\n\nDear "+getName()+"\n\nAs a valued employee of MediCo, we'd like to invite you to join the free rafle to win a holiday to " +
                "Spain, courtesy of us!\nPlease follow this link to enter: http://www.5z8.info/medico_Raffle\nEnter soon! The link expires in one hour.\n\nThe MediCo team\n\n\n" +
                "How many things can be flagged as suspicious?",
                Arrays.asList("2", "3", "4", "5"), 2,
                "Yep – you spotted them all!\n\n The shady email address of the sender and link to enter the raffle, the misspelling of the word 'raffle', and the sense of " +
                        "urgency to enter the raffle.\n\nThere are plenty of resources online explaining what to look out for in emails. A lot of them are often very subtle.\n" +
                        "One increasingly common method is the “short and sweet”. An email may simply say “here’s what you requested” and attach a malicious file.\n" +
                        "Oftentimes, people will simply click as they assume they will have just been expecting something.",
                 "The following things can be flagged as suspicious:\n- The sender's email address does not look official. Why is '84' and protonmail used?\n" +
                         "- The misspelling of 'raffle' as 'rafle'.\n- The link to enter the raffle also looks quite shady.\n- The sense of urgency - you must enter within an hour." +
                        "\n\nKeep a sharp eye out – there are plenty of other things to look out for when determining whether emails are legitimate or not.");

        Question question5 = new Question("Your CEO announced he has been receiving some very suspicious emails. " +
                "They contain details about his personal life, such as his hobby, golfing. These emails are inviting him to a \"super exclusive golfing event\". " +
                "Thankfully he's had some cyber security awareness training because... hang on.... is this phishing??\n\n" +
                "But what specific type of phishing is this?", Arrays.asList("Vishing", "Smishing", "Pharming", "Whaling"), 3,
                "'Whaling' - targetting the big fish - what an appropriate name! It turns out that through some investigation, the disgruntled employee from " +
                        "earlier decided to attack the company this way instead. He had been getting unusually friendly with the CEO recently, so he was able to gather " +
                        "some details - including the fact that he loves golf.\n\n(That employee was of course, fired!)",
                "The correct answer is 'Whaling'. This is a highly targeted attack, specifically aimed at company executives.\n\n" +
                        "It turns out that through some investigation, the disgruntled employee from earlier decided to attack the company this way instead. " +
                        "He had been getting unusually friendly with the CEO recently, so he was able to gather some details - including the fact that he loves golf.\n\n" +
                        "(That employee was of course, fired!)");

        Question question6 = new Question("The phishing email from earlier was sent to all staff, and unfortunately a couple of staff got quite excited " +
                "and clicked the link to enter. Well, this released a ransomware, that has locked all the computers across the company and demands a ransom of £50,000." +
                " Not only that, but it's spread from the company network to the manufacturing plant network!! Unless it's contained soon, this could seriously cause some damage" +
                " to the machines.\n\n" +
                "The ransomware works by propagating/self-replicating from one computer to another. Once a staff member activates this malware and it breaches the system, " +
                "no further human action is needed - it spreads by itself.\n\nWhat type of malware is defined above?", Arrays.asList("Rootkit", "Worm", "Virus", "Spyware"), 1,
                "The worm was thankfully stopped, but not before it messed with a couple of the pill manufacturing machines. MediCo is going to have " +
                        "to make sure these medicines don't leave the plant, otherwise it could cause a lot of damage financially, and hurt a lot of people physically. \n\n" +
                        "The Wannacry incident back in 2017 is an excellent example to see the potential damage of incidents like this. A ransomware released through phishing emails, locked a huge number " +
                        "of computers in the NHS. This cost the NHS £92 million, and affected a huge number of patients waiting for appointments, operations, etc.",
                "The correct answer is 'worm'. They're all very similar, but worms are the malware that self-replicate.\n\n" +
                        "The worm was thankfully stopped, but not before it messed with a couple of the pill manufacturing machines. MediCo is going to have" +
                        " to make sure these medicines don't leave the plant, otherwise it could cause a lot of damage financially, and hurt a lot of people physically.\n\n" +
                        "The Wannacry incident back in 2017 is an excellent example to see the potential damage of incidents like this. A ransomware released through phishing emails, locked a huge number " +
                        "of computers in the NHS. This cost the NHS £92 million, and affected a huge number of patients waiting for appointments, operations, etc.");

        Question question7 = new Question("The fired employee from earlier really has it out for the company!!\n\nHe launched an attack, that disrupted the traffic on MediCo's network," +
                " by overwhelming it with more Internet traffic than it can handle. The servers have gone down, in both the office and manufacturing plant. MediCo are seriously " +
                "falling behind in their medicine production, which isn't great, especially when they're the ones supplying vaccines for the current pandemic." +
                "\n\nWhat kind of attack is this?",
                Arrays.asList("Zero Day", "Man in the Middle", "Cross-site scripting", "Distributed Denial of Service"), 3,
                "He must've had plenty of resources to launch a DDoS attack! These attacks are becoming increasingly common. The 'armies' of botnets used to carry out the " +
                        "attacks are becoming more powerful and plentiful. A botnet is a network of computers infected by malware, controlled by one attacking party." +
                        "\n\nOne of the most intense DDoS attacks ever was the Amazon Web Services attack in 2020. This was " +
                        "surprising, as AWS is a cloud computing giant. The technique used targeted a specific type of vulnerable server, increasing the traffic sent " +
                        "to a victim's IP address by up to 70 times. The attack lasted three days.",
                "The attack was a Distributed Denial of Service attack - also known as DDoS attack.\n\nThese attacks are becoming increasingly common. The 'armies' of " +
                        "botnets used to carry out the attacks are becoming more powerful and plentiful. A botnet is a network of computers infected by malware, controlled by one attacking party." +
                        " One of the most intense DDoS attacks ever was the Amazon Web Services attack in 2020. This was surprising, as AWS is a cloud computing giant. " +
                        "The technique used targeted a specific type of vulnerable server, increasing the traffic sent to a victim's IP address by up to 70 times. The attack lasted three days.");

        Question question8 = new Question("Over last couple of days, some of the machinery has been acting a little strange. A worker noticed that the pills produced " +
                "are a little off in colour - they're purple, not pink. Clearly the machine has malfunctioned here. Is is possible that it has been infected?\n\n" +
                "Through an investigation, it turns out the SCADA system has been infected with a version of Stuxnet, a virus made specifically for Industrial Control Systems." +
                "\n\nThis is a major incident, and the PICERL incident response plan must be followed. What does the 'L' in PICERL stand for?",
                Arrays.asList("Logistics", "Locate", "Liabilities", "Lessons Learned"), 3,
                "Well done!\nThe PICERL plan is as follows:\n\n" +
                        "Preparation: implementing correct policies and procedures for dealing with an incident.\n" +
                        "Identification: checking whether issues in a system are innocent mistakes or malicious behaviour." +
                        "\nContainment: working to contain the incident, to prevent further damage." +
                        "\nEradication: removing any current malicious entities on the systems and preventing further unauthorised access." +
                        "\nRecovery: accessing back-up systems and following the company's recovery plan." +
                        "\nLessons Learned: examining the strengths and weaknesses of the whole process to improve the response plan for the future." +
                        "\n\nLearn this!! It may well be useful one day, you never know...",
                "The 'L' stands for 'Lessons Learned'.\nThe PICERL plan is as follows:\n\n" +
                        "Preparation: implementing correct policies and procedures for dealing with an incident.\n" +
                        "Identification: checking whether issues in a system are innocent mistakes or malicious behaviour." +
                        "\nContainment: working to contain the incident, to prevent further damage." +
                        "\nEradication: removing any current malicious entities on the systems and preventing further unauthorised access." +
                        "\nRecovery: accessing back-up systems and following the company's recovery plan." +
                        "\nLessons Learned: examining the strengths and weaknesses of the whole process to improve the response plan for the future." +
                        "\n\nLearn this!! It may well be useful one day, you never know...");

        return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5, question6, question7, question8));
    }

    @Override
    public void onClick(View v) {


        mEnableTouchEvents = false;

        if (--mNumberOfQuestions == 0) {
            endGame();
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mEnableTouchEvents = true;

                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }, 200); // LENGTH_SHORT is usually 2 second long
        }

        int responseIndex = (int) v.getTag();
        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good answer
            displayConsequence(true, mCurrentQuestion.getCorrectConsequence());
            mScore++;
        } else {
            // Wrong answer
            displayConsequence(false, mCurrentQuestion.getIncorrectConsequence());
        }
    }

    public void displayConsequence(boolean correct, String explain) {
        //open consequence activity, read consequence, then return to activity
        Intent consequence = new Intent(GameActivity.this, ConsequenceActivity.class);
        consequence.putExtra(CORRECTNESS, Boolean.valueOf(correct));
        consequence.putExtra(EXPLANATION, explain);
        startActivity(consequence);
    }


    public void endGame() {

        Intent r = new Intent();
        r.putExtra(BUNDLE_EXTRA_SCORE, mScore);

        Intent endingActivityIntent = new Intent(GameActivity.this, EndingActivity.class);
        endingActivityIntent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
        startActivity(endingActivityIntent);

        setResult(GAME_RETURN_CODE, r);
        finish();
    //finish

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setTitle("Well done!")
//                .setMessage("Your score is " + mScore)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // End the activity
//                        Intent resultIntent = new Intent();
//                        resultIntent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
//                        setResult(RESULT_OK, resultIntent);
//                        finish();
//                    }
//                })
//                .setCancelable(false)
//                .create()
//                .show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("GameActivity::onDestroy()");
    }
}