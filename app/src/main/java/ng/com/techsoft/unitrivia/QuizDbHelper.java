package ng.com.techsoft.unitrivia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ng.com.techsoft.unitrivia.QuizContract.*;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ga.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("GNS");
        addCategory(c1);
        Category c2 = new Category("POL111");
        addCategory(c2);
        Category c3 = new Category("FIN112");
        addCategory(c3);
        Category c4 = new Category("ECN112");
        addCategory(c4);
        Category c5 = new Category("EDT114");
        addCategory(c5);
    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Choose the option opposite in meaning to the world underlined\n To most people last Christmas was an \"austere\" period.",
                "severe", "harsh", "prosperous", 3,
                Question.DIFFICULTY_EASY, Category.GNS);
        addQuestion(q1);
        Question q2 = new Question("Choose the option opposite in meaning to the world underlined\n Chide is naturally \"taciturn\"",
                "friendly", "garrulous", "cheerful", 2,
                Question.DIFFICULTY_EASY, Category.GNS);
        addQuestion(q2);
        Question q3 = new Question("Choose the option opposite in meaning to the world underlined\n He is loved for his  \"altruism\"",
                "benevolence", "selfishness", "sincerity", 2,
                Question.DIFFICULTY_EASY, Category.GNS);
        addQuestion(q3);
        Question q4 = new Question("Choose the option opposite in meaning to the world underlined\n This card \"entitles\" you to attend the Glosgow",
                "disqualifies ", "disenchants", "proclaims", 1,
                Question.DIFFICULTY_EASY, Category.GNS);
        addQuestion(q4);
        Question q5 = new Question("Choose the option opposite in meaning to the world underlined\n This is an \"abridged\" edition of Oliver Twist",
                "enlarged", "outdated", "banned", 1,
                Question.DIFFICULTY_EASY,Category.GNS);
        addQuestion(q5);
        Question q6 = new Question("Choose the option opposite in meaning to the world underlined\n Our government is making  \"determined \" efforts to eradicate illiteracy",
                "compulsory", " ineffective", "innocent", 2,
                Question.DIFFICULTY_EASY, Category.GNS);
        addQuestion(q6);
        Question q7 = new Question(" Who's the father of political science",
                "Plato", "Aristotle", "socrates", 2,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q7);
        Question q8 = new Question("_____wrote the book titled Politics",
                "Aristotle", "John locke", "Plato", 1,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q8);
        Question q9 = new Question("The term politics originated from the____word known as_____",
                "Italian/Politik", "Latin/Police", "Greek/Polis", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q9);
        Question q10 = new Question("The philosophy \"The end justify the means\" is ascribed to which medieval political philosopher",
                "Aristotle", "John Locke", "Nicholo Machiavell", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q10);
        Question q11 = new Question("____defined politics as the authoritative allocation of values in the society",
                "David Easton", "B.Harold Laswe", "JeremyBentham", 1,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q11);
        Question q12 = new Question("___defined politics as who get what when and how?",
                "Harold Laswel", "Robert Dahl", "Duverge", 1,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q12);
        Question q13 = new Question("The major sub fields of political science include the following except__",
                "Comparative Politic", "Political Economy", "Political Analysis", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q13);
        Question q14 = new Question("The sub field of political science that make the study of political science to be scientific is___",
                "International Relations", "Comparative Politics", "Political Theory", 2,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q14);
        Question q15 = new Question("The following are the basic principles guiding the operation of Civil Service except__",
                "Anonymity", "Neutrality", "Federal Character", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q15);
        Question q16= new Question("The theory of Ideal state was advocated by___",
                "Aristotle", "Socrates", "Plato", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q16);
        Question q17 = new Question(".The study of Politics became scientific following the____World War",
                "Cold", "First", "Second", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q17);
        Question q18 = new Question("During the classical era,the study of politics was based on descriptive and ____era",
                "Normative", "Traditional", "Philosophical approach", 1,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q18);
        Question q20 = new Question("The sub field of political science that dwells much on local government administration and personnel management is __",
                "Public Policy Analysis", "Public Administration", "Political Theory", 2,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q20);
        Question q21 = new Question("____defined power as\"the possibility of imposing one's will upon the behaviour of other person\"",
                "Abraham Kaplan", "Max Weber", "Carl Fredrick", 2,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q21);
        Question q22 = new Question("The following are the major types of power as identified by Herbert Gold Hammer and Edward Shils in 1939",
                "Oppression", "Manipulation", "Domination", 1,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q22);
        Question q23 = new Question("_____is central to the definition of politics",
                "Legitimacy", "Campaign", "Power", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q23);
        Question q24 = new Question("The political right to use power is termed___",
                "Authority", "Legitimacy", "Sovereignty", 2,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q24);
        Question q25 = new Question("The term Authority was derived from the Latin word____meaning right",
                "Auctarua", "Auctoritas", "Auctoria", 2,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q25);
        Question q26 = new Question("Max Weber classified authority into three major ways which include the following except ___",
                "Legal-Rational", "Charismatic", "Relational", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q26);
        Question q27 = new Question("The three classification given by Max Weber was referred to as Credenda of Authority by__",
                "Charles Edward Merriam", "Carl Fredric", "Robert Dahl", 1,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q27);
        Question q28 = new Question("The right to exercise authority from customs and traditions is referred to as___",
                "Relational", "Charismatic", "Traditional", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q28);
        Question q29 = new Question("____defined influence as relation among actors such that the intention of one actor affects the predisposition to act of another is",
                "John Rhal", "Robert Dahl", "Robert John", 2,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q29);
        Question q30 = new Question(".Who wrote the book titled\"the Prince\"",
                "Nicholo Machiavelli", "Thomas Hobbes", "Auguste Comte", 1,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q30);
        Question q31 = new Question("The theory of Social Theory was expounded by the",
                "John Locke", "Jean Bodin", "Jean Jacques Roussea", 2,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q31);
        Question q32 = new Question("The book \"Leviathan\"published in 1651 was written by ___",
                "Jean Bodin", "Aristotle", "Thomas Hobbes", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q32);
        Question q33 = new Question("____is an organised group of people with common",
                "Pressure Group", "Political Party", "Public Opinion", 1,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q33);
        Question q34 = new Question("The Socio-Politically organised group of people with the sole aim of winning election and controlling the government is___",
                "Pressure Group", "Political Party", "Public Group", 2,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q34);
        Question q35 = new Question("The following countries adopt two party system",
                "UK", "USA", "Nigeria", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q35);
        Question q36 = new Question("Decentralisation is at win process of devolution and___",
                "Devolution", "Delegation", "Deregulation", 3,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q36);
        Question q37 = new Question("Who defined politics as\"war without bloodshed\"",
                "John Locke", "Chairman Mao", "None of the above", 2,
                Question.DIFFICULTY_EASY, Category.POL111);
        addQuestion(q37);
        Question q38 = new Question("_________ is the most important attribute of money",
                "Durability", "Acceptability", "Greed", 2,
                Question.DIFFICULTY_EASY, Category.FIN112);
        addQuestion(q38);
        Question q39 = new Question("The motive for holding money are _______, _______ and _______",
                "Precautionary,Speculationary and  Transactionary", "Acceptability, Speculationary and Transactionary", "Precautionary,Transactionary and Speculationary ", 3,
                Question.DIFFICULTY_EASY, Category.FIN112);
        addQuestion(q39);
        Question q40 = new Question("A legal tender means _________",
                "Any money binded by the court", "Any money binded by the Judiciary", "Any money binded by the law", 3,
                Question.DIFFICULTY_EASY, Category.FIN112);
        addQuestion(q40);
        Question q41 = new Question("_______  and _______ are the factors affecting the creation of money BY Deposit Money Banks.",
                "Leakage  and Required Reserved", "Corruption and lack of Reasources", "Indecision and Infidelity ", 3,
                Question.DIFFICULTY_EASY, Category.FIN112);
        addQuestion(q41);
        Question q42 = new Question("_____ and _____ are the two principal ways by banks can lend money to their customers",
                "loan and Bank draft", "Loan and Overdraft", "overdraft and Bank draft", 2,
                Question.DIFFICULTY_EASY, Category.FIN112);
        addQuestion(q42);
        Question q43 = new Question("The most critical drawable of the barter system is that of _________",
                "Double Coincidence of wants", "Double Coincidence of want", "Double Coincidence of needs", 2,
                Question.DIFFICULTY_EASY, Category.FIN112);
        addQuestion(q43);
        Question q44 = new Question("The major role of the Central Bank of Nigeria(CBN) is _________",
                "is to issue checks and cash ", "is to issue out currency and monetary policy", "is to issue money and imflate the market", 2,
                Question.DIFFICULTY_EASY, Category.FIN112);
        addQuestion(q44);
        Question q45 = new Question("_______ are the first indigenous bank in Nigeria was created in ________",
                "industrial and islamic bank & 1928", "Financial  and commercial bank & 1939", "industrial and commercial bank & 1929", 3,
                Question.DIFFICULTY_EASY, Category.FIN112);
        addQuestion(q45);
        Question q46 = new Question("For money to serve as a medium of exchange, its must be _______  by the users",
                "Acceptable", "Recognize ", "Traded", 3,
                Question.DIFFICULTY_EASY, Category.FIN112);
        addQuestion(q46);
        Question q47 = new Question("The major problem of trade by barter is ...... ",
                "double coincidence of wants", "lack of specialization", "storing value", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q47);
        Question q48 = new Question("The development of paper money started with...... ",
                "law of  country", "formation of banks", "goldsmiths", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q48);
        Question q49 = new Question("Which of the following is not a quality of money",
                "divisibility", "medium of exchange", "acceptability", 2,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q49);
        Question q50 = new Question("Which of the following qualities of money is generally undermined by inflation",
                "stability", "durability", "portability", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q50);
        Question q51 = new Question("The process of exchange goods for goods is called ....",
                "double coincidence of wants", "indirect trade", "barter system", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q51);
        Question q52 = new Question("One of the major disadvantages of commodity money is...",
                "indivisibility", "not legal tender", "portability", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q52);
        Question q53 = new Question("...... are money that serves as the store of value temporarily and is convertible into medium of exchange",
                "legal tender", "near money", "fiat money", 2,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q53);
        Question q54 = new Question("..... are money deposited with financial institutions",
                "bank deposits", "cheque", "credit account", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q54);
        Question q55 = new Question("...... is a representative money whose intrinsic value of metal is less than its face value",
                "fiat money", "fiduciary money", "token money", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q55);
        Question q56 = new Question("...... is a form of payment, which by law a creditor is compelled to accept in settlement of a debt",
                "bank money", "credit money", "legal tender", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q56);
        Question q57 = new Question("...... are small pieces of precious metals issued and stamped by the Central Bank as a medium of exchange",
                "near money", "gold back money", "coins", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q57);
        Question q58 = new Question("The issue of ....... by the goldsmith led to the development of bank notes",
                "gold", "receipts", "coins", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q58);
        Question q59 = new Question("The three types of deposit money are.....",
                "demand,current,savings", "savings,current,demand", "demand,savings,time", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q59);
        Question q60 = new Question("Money should be ....... so as not to lose its value",
                "divisible", "durable", "relatively scarce", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q60);
        Question q61 = new Question("Having #50,#100,#200,#500,#1000 of Nigerian currency result from a quality of money known as......",
                "portability", "divisibility", "divisibility", 2,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q61);
        Question q62 = new Question("'Money allows credit system' is derived from a function of money known as....",
                "standard for deferred payment", "medium of exchange", "measure of value", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q62);
        Question q63 = new Question("Liquidity preference is known as....",
                "supply of money", "velocity of money", "demand for money", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q63);
        Question q64 = new Question("The motives for holding money are.... ",
                "transactionary,precautionary,emergency", "transactionary,precautionary,speculative ", "transactionary,savings,precautionary", 2,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q64);
        Question q65 = new Question("The motive for holding money known as 'Liquidity preference of money' is called....",
                "transactionary motive", "precautionary motive ", " speculative motive", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q65);
        Question q66 = new Question("The motive(s) of holding that depends solely upon the 'level of income' is/are.....",
                "transactionary,precautionary", " transactionary ", "transactionary, precautionary,speculative", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q66);
        Question q67 = new Question("...... is anything intended to serve as a medium of exchange which has a legal tender",
                "partial money", "fiat money ", "fiduciary note", 2,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q67);
        Question q68 = new Question("......... is the total amount of money all the people in an economy desire to hold ",
                "supply of money", "currency  ", "demand for money", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q68);
        Question q69 = new Question("....... defined money as the currency in circulation (coins and notes) plus demand deposit plus time and savings of commercial bank",
                "broad money", "narrow money ", "broader", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q69);
        Question q70 = new Question("There are....... components of money ",
                "three", " two", "five", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q70);
        Question q71 = new Question("M1 defines money as the total currency in circulation plus....... ",
                "demand deposit plus time", "demand deposit plus time, savings,investments,loans etc", "demand deposit", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q71);
        Question q72 = new Question("One of these is not a factor affecting supply of money",
                "discount rate", "cash reserve", "total", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q72);
        Question q73 = new Question("...... is the total stock of money available for use in an economy at a given period of time",
                "supply of money", "currency", "demand for money", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q73);
        Question q74 = new Question("The quantity theory of money was improved by",
                "Irvying Fisher", "Irving Fisher", "Irving Fishering", 2,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q74);
        Question q75 = new Question("MV= PQ where Q means",
                "quantity of money ", "equilibrium of output", "real output", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q75);
        Question q76 = new Question("......... is the average number of times unit of money is put into use during a period ",
                " velocity of money", "GDP ", "money supply", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q76);
        Question q77 = new Question("According to the quantity theory of money,inflation will be caused by.... ",
                " high velocity of money", "increase in money supply ", "increase in real GNP", 2,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q77);
        Question q78 = new Question("It is assumed that PQ equals.... ",
                " GNP", "GDP", "quantity theory of money", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q78);
        Question q79 = new Question("Greham's law of money state that.....",
                "good money drives out bad money ", " bad money brings good money", "bad money drives out good money", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q79);
        Question q80 = new Question("Currency debasement means..... ",
                "worn out of coins and paper notes", "tearing of coins and paper notes ", "hoarding of coins and paper notes", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q80);
        Question q81 = new Question("One of the criticisms of quantity theory of money is ..... ",
                " the theory consider interest rate ", " it is not a theory but ways of showing relationship between variables ", "MV is equal to PQ", 2,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q81);
        Question q82 = new Question("The Central Bank of Nigeria was established in.... ",
                "1959 ", " 1958 ", "1957", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q82);
        Question q83 = new Question("The apex bank in a country is.... ",
                " commercial bank ", "financial institutions", "central bank", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q83);
        Question q84 = new Question("The sales and purchases of securities from and to the commercial banks to control the circulation of money is called......",
                " fiscal policy", "open market operation", "public finance", 2,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q84);
        Question q85 = new Question("The certain percentage required by the commercial banks to have as deposit with the central bank is known as.....",
                " liquidity ratio", "bank rate", "special deposit", 1,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q85);
        Question q86 = new Question("If the central bank increases the bank rate, this will discourage...",
                "lenders", "spenders", "borrowers", 3,
                Question.DIFFICULTY_EASY, Category.ECN112);
        addQuestion(q86);
        Question q87 = new Question("MS OFFICE Is Application Software",
                "yes", "no", " none of the above", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q87);
        Question q88 = new Question("Thesaurus tool in MS Word is used for",
                "Spelling suggestions", "none of the above", " Synonyms and Antonyms words", 3,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q87);
        Question q89 = new Question("The minimum number of rows and columns in MS Word document is",
                " 2 and 1 ", "1 and 1 ", "2 and 2", 2,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q89);
        Question q90 = new Question("How many columns can you insert in a word document in maximum?",
                "45", "40 Hi", "50", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q90);
        Question q91 = new Question("What is the smallest and largest font size available in Font Size tool on formatting toolbar?",
                "6 and 68", "6 and 72 ", "8 and 72 ", 3,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q91);
        Question q92 = new Question("A character that is raised and smaller above the baseline is known as",
                " Raised ", " Capscript", "Superscript", 3,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q92);
        Question q93 = new Question("Selecting text means, selecting?",
                "  any of the above", " an entire sentence ", "a word ", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q93);
        Question q94 = new Question("MS-Word automatically moves the text to the next line when it reaches the right edge of the screen and is called?",
                " Enter ", "Word Wrap ", "Carriage Return ", 2,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q94);
        Question q95 = new Question("Using Find command in Word, we can search?",
                "characters ", "All of the above", "formats", 2,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q95);
        Question q96 = new Question("In MS-Word, for what does ruler help?",
                "All of the above ", "to change page margins 24%", "to set tabs 0%", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q96);
        Question q97 = new Question("By default, on which page the header or the footer is printed?",
                "on alternate page", "on first page ", "on every page ", 3,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q97);
        Question q98 = new Question("Which menu in MSWord can be used to change character size and typeface?",
                "Format", "View ", " Tools ", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q98);
        Question q99 = new Question("Which key should be pressed to start a new paragraph in MS-Word?",
                "Down Cursor Key ", "Enter Key ", "Shift + Enter", 2,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q99);
        Question q100 = new Question("Which bar is usually located below that Title Bar that provides categorized options?",
                "Status Bar ", "Menu bar ", "Scroll bar", 2,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q100);
        Question q101 = new Question("Which of these toolbars allows changing of Fonts and their sizes?",
                "Formatting ", "Standard ", "None of these", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q101);
        Question q102 = new Question("Which option in File pull-down menu is used to close a file in MSWord?",
                "Close ", "New ", "Exit", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q102);
        Question q103 = new Question("What is the function of Ctrl + B in Ms-Word",
                "It applies Italic formatting t the selected text", "It converts selected text into the next larger size of the same font ", "It makes the selected text bold ", 3,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q103);
        Question q104 = new Question("Graphics for word processor",
                " Clip art ", " Execute", " Peripheral ", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q104);
        Question q105 = new Question("What is the function of CTRL+R in MS-Word",
                "Close the current window ", "Open the Print dialog box ", "None of these", 3,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q105);
        Question q106 = new Question("What is the extension of files created in Ms-Word 97- 2003",
                "dom", "doc ", "dot", 2,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q106);
        Question q107 = new Question("In Microsoft Word shortcut key CTRL+W is used for",
                "close the current window", " open the Print dialog box ", "Update the current Web page", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q107);
        Question q108 = new Question("Which shortcut key is used to spell check in MS-Word",
                "F7", "F2 ", "F1", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q108);
        Question q109 = new Question("Why headers and footers used in MS -Word",
                "To allow page headers and footers to appear on document when it is printed", "To enhance the overall appearance of the document ", "To mark the starting of a page ", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q109);
        Question q110 = new Question("The minimum number of rows and columns a word table can have is",
                "Zero ", "CTRL+RIGHTARROW 2 rows and 1 column ", "1 row and 1 column", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q110);
        Question q111 = new Question("In MS-Word shortcut SHIFT+DELETE is used to",
                "Create a shortcut to the selected item", "Rename the selected item", "Delete the selected item permanently without placing the item in the Recycle Bin ", 3,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q111);
        Question q112 = new Question(". In MS Word to move the insertion point to the beginning of the next word command used is",
                "CTRL+RIGHTARROW", "CTRL+DOWN ARROW", " CTRL+LEFT ARROW", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q112);
        Question q113 = new Question("What is the default number of lines to drop for drop cap?",
                " 10 ", "3", "15", 2,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q113);
        Question q114 = new Question("A bookmark is an item or location in document that you identify a name for future Reference. Which of the following task is accomplished by using bookmarks?",
                "To add hyperlinks in a web page ", "To quickly jump to a specific location in the document", "To mark the ending of a page of document ", 2,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q114);
        Question q115 = new Question("Why are headers and footers used in document?",
                "To allow page headers and footers to appear on document when it is printed", "To mark the starting and ending of a page ", "To mark large document more readable", 1,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q115);
        Question q116 = new Question("What is the maximum number of lines you can set for a drop cap?",
                " 3 ", "10", "11", 2,
                Question.DIFFICULTY_EASY, Category.EDT114);
        addQuestion(q116);



    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}