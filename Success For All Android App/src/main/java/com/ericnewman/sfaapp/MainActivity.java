package com.ericnewman.sfaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.ericnewman.sfaapp.database.ConcernsAdapter;
import com.ericnewman.sfaapp.database.SQLiteHelper;
import com.ericnewman.sfaapp.databinding.ActivityMainBinding;
import com.ericnewman.sfaapp.dto.ConcernsDTO;
import com.ericnewman.sfaapp.dto.DetailActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static ArrayList<ConcernsDTO> concernsList = new ArrayList<ConcernsDTO>();
    ListView listView;
    private SearchView searchView;
    MenuItem logoutMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.academic.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AcademicActivity.class);
            startActivity(intent);
        });
        binding.laws.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LawsActivity.class);
            startActivity(intent);
        });
        binding.behavior.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BehaviorActivity.class);
            startActivity(intent);
        });
        binding.main.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        });
        settingsOnClick();
        setupData();
        setUpList();
        setUpOnclickListener();
        logoutMenuItem = binding.main.findViewById(R.id.action_logout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menuItem.getActionView(); // Get the SearchView from the menuItem

        searchView.setQueryHint("Search for a concern");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        return true; // Return true to indicate that the menu has been successfully created
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_search) {
            // Handle search icon click
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void searchOnClick(MenuItem menuItem) {
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });
    }

    private void filterList(String keyword) {
        ArrayList<ConcernsDTO> filteredConcerns = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase();

        for (ConcernsDTO concernDTO : concernsList) {
            String concernsName = concernDTO.getConcernsName().toLowerCase();

            if (concernsName.contains(lowerCaseKeyword)) {
                filteredConcerns.add(concernDTO);
            }
        }

        ConcernsAdapter adapter = new ConcernsAdapter(getApplicationContext(), 0, filteredConcerns);
        listView.setAdapter(adapter);
    }
        public void settingsOnClick() {
            // Create an instance of your database helper class
            SQLiteHelper databaseHelper = new SQLiteHelper(this);

            // Check if the user is logged in
            boolean isLoggedIn = databaseHelper.getLoggedInStatus();

            // If the user is logged in, allow them to access the settings activity
            if (isLoggedIn) {
                // Allow access to the settings activity
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            } else {
                // User is not logged in, show a message or redirect to the login activity
                Toast.makeText(MainActivity.this, "Please log in to access settings", Toast.LENGTH_SHORT).show();
                // Or, redirect to the login activity
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }

            // Close the database connection (if needed)
            databaseHelper.close();
        }

    public void logoutOnClick(MenuItem menuItem){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Logout").setMessage("Confirm logout");
            builder.setPositiveButton("Yes", (dialog, id) -> {
                // Perform logout operations here
                // For example, clear the logged-in status and redirect to the login activity

                // Clear the logged-in status (e.g., using shared preferences)
                SQLiteHelper databaseHelper = new SQLiteHelper(MainActivity.this);
                databaseHelper.setLoggedInStatus(false);
                databaseHelper.close();

                // Redirect to the login activity
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish(); // Optional: Close the current activity to prevent going back
            });
            builder.setNegativeButton("No", (dialog, id) -> dialog.cancel());
            AlertDialog alert11 = builder.create();
            alert11.show();
        }

    public void loginOnClick(MenuItem menuItem){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void setUpList() {
        listView = findViewById(R.id.listview);

        ConcernsAdapter adapter = new ConcernsAdapter(getApplicationContext(), 0, concernsList);
        listView.setAdapter(adapter);
    }

    private void setUpOnclickListener(){
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            ConcernsDTO selectConcern = (ConcernsDTO) (listView.getItemAtPosition(position));
            Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
            showDetail.putExtra("id",selectConcern.getConcernsId());
            startActivity(showDetail);
        });

    }





    /**
     * Hard coded data
     */
    private void setupData(){
        int concernsId = 0;
        ConcernsDTO hyperactive = new ConcernsDTO(
                concernsId,
                "hyperactive",
                "behavior",
                "attention",
                "roaming",
                "A hyperactive child displaying roaming behavior in the classroom or home may exhibit excessive movement, restlessness, and a persistent need to explore or move around the environment. The roaming behavior may be a result of the child seeking sensory stimulation, attention, or a sense of control. Understanding and addressing the underlying factors contributing to the hyperactivity and roaming tendencies can be beneficial in supporting the child's engagement, focus, and overall behavior in the learning environment.");
        concernsList.add(hyperactive);

        ConcernsDTO doesNotPayAttention = new ConcernsDTO(
                concernsId++,
                "does not pay attention",
                "behavior",
                "focus",
                "in-attetion",
                "A child displaying inattentive behavior in the classroom or home may struggle with maintaining focus, being easily distracted, and having difficulty following instructions. Addressing the underlying factors contributing to the inattentiveness can be beneficial in supporting the child's engagement, concentration, and overall behavior in the learning environment.");
        concernsList.add(doesNotPayAttention);

        ConcernsDTO doesNotCare = new ConcernsDTO(
                concernsId++,"does not care",
                "behavior",
                "developmental issues",
                "apathy",
                "A child displaying apathetic behavior in the classroom or home may exhibit a lack of interest, motivation, or enthusiasm towards tasks or activities. Understanding and addressing the underlying factors contributing to the apathy can be beneficial in fostering the child's engagement, curiosity, and overall behavior in the learning environment.");
        concernsList.add(doesNotCare);


        ConcernsDTO chatting = new ConcernsDTO(
                concernsId++,
                "chatting",
                "behavior",
                "attention",
                "off-task talking",
                "A child engaging in off-task talking in the classroom or home may frequently engage in unnecessary or unrelated conversations, disrupting the learning environment. Implementing strategies to promote focused and attentive behavior can be beneficial in encouraging the child to stay on task and contribute positively to the learning environment."
        );
        concernsList.add(chatting);

        ConcernsDTO immature = new ConcernsDTO(
                concernsId++,
                "immature",
                "behavior",
                "developmental issues",
                "off-task",
                "A child displaying off-task behavior in the classroom or home may exhibit a lack of focus, easily getting distracted, and engaging in activities that are not relevant to the task at hand. Addressing the underlying factors contributing to the immature behavior and promoting age-appropriate focus and task engagement can be beneficial in fostering a productive learning environment."
        );
        concernsList.add(immature);

        ConcernsDTO disruptive = new ConcernsDTO(
                concernsId++,
                "disruptive",
                "behavior",
                "developmental issues",
                "disruption",
                "A child engaging in disruptive behavior in the classroom or home may frequently interrupt activities, distract peers, or hinder the learning process. Implementing strategies to manage and redirect the disruptive behavior can be beneficial in creating a more focused and productive learning environment for the child and others."
        );
        concernsList.add(disruptive);

        ConcernsDTO liar = new ConcernsDTO(
                concernsId++,
                "liar",
                "behavior",
                "deceiving",
                "lying",
                "A child displaying lying behavior in the classroom or home may engage in deceptive or untruthful statements, impacting trust and relationships. Addressing the underlying factors contributing to the lying behavior and promoting honesty and integrity can be beneficial in fostering a positive and trustworthy learning environment."
        );
        concernsList.add(liar);

        ConcernsDTO cheater = new ConcernsDTO(
                concernsId++,"cheater",
                "behavior",
                "developmental issues",
                "cheating",
                "A child engaging in cheating behavior in the classroom or home may attempt to gain an unfair advantage by dishonest means, compromising the integrity of assessments or tasks. Implementing strategies to promote a sense of fairness, academic integrity, and ethical behavior can be beneficial in discouraging cheating and fostering a positive learning environment.");
        concernsList.add(cheater);

        ConcernsDTO thief = new ConcernsDTO(
                concernsId++,
                "thief",
                "behavior",
                "personal or family problems",
                "stealing",
                "A child displaying stealing behavior in the classroom or home may take belongings or resources that do not belong to them, violating boundaries and trust. Addressing the underlying factors contributing to the stealing behavior and promoting respect for others' property can be beneficial in fostering a safe and supportive learning environment."
        );
        concernsList.add(thief);

        ConcernsDTO violent = new ConcernsDTO(
                concernsId++,
                "violent",
                "behavior",
                "attention",
                "aggression or fighting",
                "A hyperactive child displaying aggressive or fighting behavior in the classroom or home may engage in physical or verbal confrontations, posing risks to their own well-being and that of others. Implementing strategies to manage anger, promote conflict resolution, and foster positive social interactions can be beneficial in creating a safe and inclusive learning environment."
        );
        concernsList.add(violent);

        ConcernsDTO misconduct = new ConcernsDTO(
                concernsId++,
                "misconduct",
                "behavior",
                "attention",
                "malicious mischief",
                "A child engaging in malicious mischief behavior in the classroom or home may exhibit intentionally harmful or destructive actions towards objects, property, or peers. Addressing the underlying factors contributing to the malicious mischief behavior and promoting responsibility, empathy, and appropriate ways to channel energy can be beneficial in fostering a positive and respectful learning environment."
        );
        concernsList.add(misconduct);

        ConcernsDTO disobedience = new ConcernsDTO(
                concernsId++,
                "disobedience",
                "behavior",
                "personal or family problems",
                "defiance of authority",
                "A child displaying defiance of authority in the classroom or home may exhibit resistance, disobedience, or challenges towards rules or instructions. Implementing strategies to promote positive discipline, establish clear expectations, and foster respectful relationships can be beneficial in addressing the defiance and creating a structured and supportive learning environment."
        );
        concernsList.add(disobedience);

        ConcernsDTO difficultySoundingOutWords = new ConcernsDTO(
                concernsId++,
                "difficulty sounding out words",
                "academic",
                "reading",
                "phonics",
                "A child experiencing difficulty sounding out words during reading may struggle with phonics, which is the ability to connect sounds with letters or letter combinations. This difficulty can impact their overall reading fluency and comprehension. Addressing the underlying factors contributing to the difficulty in phonics can be beneficial in supporting the child's reading development. Providing targeted instruction, multisensory activities, and practice with phonics skills can help improve their ability to decode words and enhance their reading abilities."
        );
        concernsList.add(difficultySoundingOutWords);

        ConcernsDTO difficultyWithPhonemicAwareness = new ConcernsDTO(
                concernsId++,
                "difficulty with phonemic awareness",
                "academic",
                "reading",
                "phonemic awareness",
                "A child experiencing difficulty with phonemic awareness may struggle to identify and manipulate individual sounds in spoken words. Phonemic awareness is crucial for developing reading and spelling skills. Addressing the underlying factors contributing to the difficulty in phonemic awareness can be beneficial in supporting the child's reading development. Providing explicit instruction, phonemic awareness activities, and practice with recognizing and manipulating sounds can help improve their phonemic awareness skills and enhance their overall reading abilities."
        );
        concernsList.add(difficultyWithPhonemicAwareness);

        ConcernsDTO difficultyWithLetterRecognition = new ConcernsDTO(
                concernsId++,
                "difficulty with letter recognition",
                "academic",
                "reading",
                "letter recognition",
                "A child experiencing difficulty with letter recognition may struggle to identify and differentiate individual letters. Letter recognition is fundamental for building reading and writing skills. Addressing the underlying factors contributing to the difficulty in letter recognition can be beneficial in supporting the child's reading development. Providing engaging activities, letter-sound correspondence practice, and exposure to print can help improve their letter recognition skills and promote their overall reading abilities."
        );
        concernsList.add(difficultyWithLetterRecognition);

        ConcernsDTO difficultyWithWordRecognition = new ConcernsDTO(
                concernsId++,
                "difficulty with word recognition",
                "academic",
                "reading",
                "word recognition",
                "A child experiencing difficulty with word recognition may struggle to quickly and accurately identify familiar words. Word recognition is essential for fluent reading and comprehension. Addressing the underlying factors contributing to the difficulty in word recognition can be beneficial in supporting the child's reading development. Providing repeated exposure to high-frequency words, word recognition activities, and strategies for decoding unfamiliar words can help improve their word recognition skills and enhance their overall reading abilities."
        );
        concernsList.add(difficultyWithWordRecognition);

        ConcernsDTO difficultyWithReadingComprehension = new ConcernsDTO(
                concernsId++,
                "difficulty with reading comprehension",
                "academic",
                "reading",
                "comprehension",
                "A child experiencing difficulty with reading comprehension may struggle to understand and make meaning from texts. Reading comprehension involves various skills, including vocabulary knowledge, inferencing, and understanding text structures. Addressing the underlying factors contributing to the difficulty in reading comprehension can be beneficial in supporting the child's overall reading development. Providing explicit comprehension strategies, vocabulary instruction, and opportunities for practicing comprehension skills can help improve their reading comprehension abilities."
        );
        concernsList.add(difficultyWithReadingComprehension);

        ConcernsDTO difficultyWithVocabulary = new ConcernsDTO(
                concernsId++,
                "difficulty with vocabulary",
                "academic",
                "reading",
                "vocabulary",
                "A child experiencing difficulty with vocabulary may struggle to understand and use a wide range of words. Vocabulary knowledge plays a crucial role in reading comprehension and academic success. Addressing the underlying factors contributing to the difficulty in vocabulary can be beneficial in supporting the child's reading development. Providing explicit vocabulary instruction, context-rich activities, and exposure to a variety of texts can help improve their vocabulary skills and enhance their overall reading abilities."
        );
        concernsList.add(difficultyWithVocabulary);



        ConcernsDTO difficultyWithReadingFluency = new ConcernsDTO(
                concernsId++,
                "difficulty with reading fluency",
                "academic",
                "reading",
                "fluency for reading",
                "A child experiencing difficulty with reading fluency may read slowly, word by word, and lack prosody or expression. Reading fluency is important for comprehension and engagement in reading. Addressing the underlying factors contributing to the difficulty in reading fluency can be beneficial in supporting the child's reading development. Providing opportunities for repeated reading, modeling fluent reading, and using appropriate reading strategies can help improve their reading fluency and enhance their overall reading abilities."
        );
        concernsList.add(difficultyWithReadingFluency);

        ConcernsDTO adhdReadingComprehension = new ConcernsDTO(
                concernsId++,
                "adhd",
                "academic",
                "reading",
                "comprehension",
                "A child experiencing difficulty with reading comprehension due to ADHD may struggle to understand and make meaning from texts due to executive functioning deficits. Reading comprehension involves various skills, including vocabulary knowledge, inferencing, and understanding text structures. Addressing the underlying factors contributing to the difficulty in reading comprehension can be beneficial in supporting the child's overall reading development. Providing explicit comprehension strategies, vocabulary instruction, and opportunities for practicing comprehension skills can help improve their reading comprehension abilities."
        );
        concernsList.add(adhdReadingComprehension);

        ConcernsDTO adhdOffTask1 = new ConcernsDTO(
                concernsId++,
                "adhd",
                "behavior",
                "attention",
                "off-task",
                "A hyperactive child displaying roaming behavior in the classroom or home may exhibit excessive movement, restlessness, and a persistent need to explore or move around the environment. The roaming behavior may be a result of the child seeking sensory stimulation, attention, or a sense of control. Understanding and addressing the underlying factors contributing to the hyperactivity and roaming tendencies can be beneficial in supporting the child's engagement, focus, and overall behavior in the learning environment."
        );
        concernsList.add(adhdOffTask1);

        ConcernsDTO adhdOffTask2 = new ConcernsDTO(
                concernsId++,
                "adhd",
                "behavior",
                "attention",
                "off-task",
                "A hyperactive child displaying roaming behavior in the classroom or home may exhibit excessive movement, restlessness, and a persistent need to explore or move around the environment. The roaming behavior may be a result of the child seeking sensory stimulation, attention, or a sense of control. Understanding and addressing the underlying factors contributing to the hyperactivity and roaming tendencies can be beneficial in supporting the child's engagement, focus, and overall behavior in the learning environment."
        );
        concernsList.add(adhdOffTask2);

        ConcernsDTO difficultyWithLetterRecognition2 = new ConcernsDTO(
                concernsId++,
                "difficulty with letter recognition",
                "academic",
                "reading",
                "letter recognition",
                "A child experiencing difficulty with letter recognition may struggle to identify and differentiate individual letters. This does not mean they have dyslexia. Letter recognition is fundamental for building reading and writing skills. Addressing the underlying factors contributing to the difficulty in letter recognition can be beneficial in supporting the child's reading development. Providing engaging activities, letter-sound correspondence practice, and exposure to print can help improve their letter recognition skills and promote their overall reading abilities."
        );
        concernsList.add(difficultyWithLetterRecognition2);

        ConcernsDTO difficultyWithNumberRecognition = new ConcernsDTO(
                concernsId++,
                "difficulty with number recognition",
                "academic",
                "math",
                "number recognition",
                "A child experiencing difficulty with number recognition may struggle to identify and differentiate individual numbers. Number recognition is fundamental for building math skills. Addressing the underlying factors contributing to the difficulty in number recognition can be beneficial in supporting the child's math development. Providing engaging activities, number-sense practice, and exposure to numerical concepts can help improve their number recognition skills and promote their overall math abilities."
        );
        concernsList.add(difficultyWithNumberRecognition);
        ConcernsDTO difficultyWithNumberSense = new ConcernsDTO(
                concernsId++,
                "difficulty with number sense",
                "academic",
                "math",
                "number sense",
                "A child experiencing difficulty with number sense may struggle to understand the relationships between numbers and their magnitude. Number sense is essential for developing a strong foundation in mathematics. Addressing the underlying factors contributing to the difficulty in number sense can be beneficial in supporting the child's math development. Providing hands-on activities, number sense exercises, and real-world applications of math concepts can help improve their number sense skills and promote their overall math abilities."
        );
        concernsList.add(difficultyWithNumberSense);

        ConcernsDTO difficultyWithArithmetic = new ConcernsDTO(
                concernsId++,
                "difficulty with arithmetic",
                "academic",
                "math",
                "arithmetic",
                "A child experiencing difficulty with arithmetic may struggle with basic mathematical operations such as addition, subtraction, multiplication, and division. Arithmetic skills are foundational for more advanced math concepts. Addressing the underlying factors contributing to the difficulty in arithmetic can be beneficial in supporting the child's math development. Providing structured practice, visual aids, and real-life problem-solving opportunities can help improve their arithmetic skills and promote their overall math abilities."
        );
        concernsList.add(difficultyWithArithmetic);

        ConcernsDTO difficultyWithAlgebra = new ConcernsDTO(
                concernsId++,
                "difficulty with algebra",
                "academic",
                "math",
                "algebra",
                "A child experiencing difficulty with algebra may struggle with solving equations, understanding variables, and applying algebraic concepts. Algebra is a critical branch of mathematics for problem-solving and higher-level math courses. Addressing the underlying factors contributing to the difficulty in algebra can be beneficial in supporting the child's math development. Providing step-by-step guidance, visual representations, and real-life applications of algebraic concepts can help improve their algebra skills and promote their overall math abilities."
        );
        concernsList.add(difficultyWithAlgebra);

        ConcernsDTO difficultyWithGeometry = new ConcernsDTO(
                concernsId++,
                "difficulty with geometry",
                "academic",
                "math",
                "geometry",
                "A child experiencing difficulty with geometry may struggle with understanding shapes, spatial relationships, and geometric concepts. Geometry plays a crucial role in visualizing and analyzing the properties of objects. Addressing the underlying factors contributing to the difficulty in geometry can be beneficial in supporting the child's math development. Providing hands-on activities, visual aids, and real-world examples can help improve their geometry skills and promote their overall math abilities."
        );
        concernsList.add(difficultyWithGeometry);

        ConcernsDTO difficultyWithLetterFormation = new ConcernsDTO(
                concernsId++,
                "difficulty with letter formation",
                "academic",
                "writing",
                "letter formation",
                "A child experiencing difficulty with letter formation may struggle to correctly write letters in a legible and consistent manner. Letter formation is an important aspect of developing good handwriting skills. Addressing the underlying factors contributing to the difficulty in letter formation can be beneficial in supporting the child's writing development. Providing guided practice, fine motor exercises, and proper pencil grip techniques can help improve their letter formation skills and promote their overall writing abilities."
        );
        concernsList.add(difficultyWithLetterFormation);

        ConcernsDTO difficultyWithGrammar = new ConcernsDTO(
                concernsId++,
                "difficulty with grammar",
                "academic",
                "writing",
                "grammar",
                "A child experiencing difficulty with grammar may struggle with understanding and applying the rules of language in their writing. Grammar skills are essential for clear and effective communication. Addressing the underlying factors contributing to the difficulty in grammar can be beneficial in supporting the child's writing development. Providing explicit instruction, practice exercises, and feedback on grammar concepts can help improve their grammar skills and promote their overall writing abilities."
        );
        concernsList.add(difficultyWithGrammar);

        ConcernsDTO difficultyWithSpelling = new ConcernsDTO(
                concernsId++,
                "difficulty with spelling",
                "academic",
                "writing",
                "spelling",
                "A child experiencing difficulty with spelling may struggle with accurately spelling words in their writing. Spelling skills contribute to written communication and language development. Addressing the underlying factors contributing to the difficulty in spelling can be beneficial in supporting the child's writing development. Providing explicit instruction, word study activities, and opportunities for practice and reinforcement can help improve their spelling skills and promote their overall writing abilities."
        );
        concernsList.add(difficultyWithSpelling);

        ConcernsDTO difficultyWithFluency = new ConcernsDTO(
                concernsId++,
                "difficulty with fluency",
                "academic",
                "writing",
                "fluency",
                "A child experiencing difficulty with fluency may struggle with writing smoothly and maintaining a consistent pace. Writing fluency is important for expressing ideas effectively and coherently. Addressing the underlying factors contributing to the difficulty in fluency canbe beneficial in supporting the child's writing development. Providing timed writing exercises, scaffolding techniques, and opportunities for repeated practice can help improve their writing fluency and promote their overall writing abilities."
        );
        concernsList.add(difficultyWithFluency);
        ConcernsDTO difficultyWithComposition = new ConcernsDTO(
                concernsId++,
                "difficulty with composition",
                "academic",
                "writing",
                "composition",
                "A child experiencing difficulty with composition may struggle with organizing ideas, structuring sentences and paragraphs, and developing a coherent piece of writing. Composition skills are crucial for effective written communication. Addressing the underlying factors contributing to the difficulty in composition can be beneficial in supporting the child's writing development. Providing writing prompts, graphic organizers, and guidance on the writing process can help improve their composition skills and promote their overall writing abilities."
        );
        concernsList.add(difficultyWithComposition);

        ConcernsDTO difficultyWithFocus = new ConcernsDTO(
                concernsId++,
                "attention",
                "behavior",
                "focus",
                "in-attention",
                "A child displaying inattentive behavior in the classroom or home may struggle with maintaining focus, being easily distracted, and having difficulty following instructions. Addressing the underlying factors contributing to the inattentiveness can be beneficial in supporting the child's engagement, concentration, and overall behavior in the learning environment."
        );
        concernsList.add(difficultyWithFocus);

        ConcernsDTO difficultyWithReadingFluencyAgain = new ConcernsDTO(
                concernsId++,
                "fluency",
                "academic",
                "reading",
                "fluency for reading",
                "A child experiencing difficulty with reading fluency may read slowly, word by word, and lack prosody or expression. Reading fluency is important for comprehension and engagement in reading. Addressing the underlying factors contributing to the difficulty in reading fluency can be beneficial in supporting the child's reading development. Providing opportunities for repeated reading, modeling fluent reading, and using appropriate reading strategies can help improve their reading fluency and enhance their overall reading abilities."
        );
        concernsList.add(difficultyWithReadingFluencyAgain);

        ConcernsDTO difficultyWithComprehensionAgain = new ConcernsDTO(
                concernsId++,
                "comprehension",
                "academic",
                "reading",
                "comprehension",
                "A child experiencing difficulty with reading comprehension may struggle to understand and make meaning from texts. Reading comprehension involves various skills, including vocabulary knowledge, inferencing, and understanding text structures. Addressing the underlying factors contributing to the difficulty in reading comprehension can be beneficial in supporting the child's overall reading development. Providing explicit comprehension strategies, vocabulary instruction, and opportunities for practicing comprehension skills can help improve their reading comprehension abilities."
        );
        concernsList.add(difficultyWithComprehensionAgain);

        ConcernsDTO difficultyWithAggression = new ConcernsDTO(
                concernsId++,
                "aggresive",
                "behavior",
                "attention",
                "aggression or fighting",
                "A hyperactive child displaying aggressive or fighting behavior in the classroom or home may engage in physical or verbal confrontations, posing risks to their own well-being and that of others. Implementing strategies to manage anger, promote conflict resolution, and foster positive social interactions can be beneficial in creating a safe and inclusive learning environment."
        );
        concernsList.add(difficultyWithAggression);

        ConcernsDTO difficultyWithLetterRecognitionAgain = new ConcernsDTO(
                concernsId++,
                "letters",
                "academic",
                "reading",
                "letter recognition",
                "A child experiencing difficulty with letter recognition may struggle to identify and differentiate individual letters. This does not mean they have dyslexia. Letter recognition is fundamental for building reading and writing skills. Addressing the underlying factors contributing to the difficulty in letter recognition can be beneficial in supporting the child's reading development. Providing engaging activities, letter-sound correspondence practice, and exposure to print can help improve their letter recognition skills and promote their overall reading abilities."
        );
        concernsList.add(difficultyWithLetterRecognitionAgain);

        ConcernsDTO difficultyWithNumberRecognitionAgain = new ConcernsDTO(
                concernsId++,
                "numbers",
                "academic",
                "math",
                "number recognition",
                "A child experiencing difficulty with number recognition may struggle to identify and differentiate individual numbers. Number recognition is fundamental for building math skills. Addressing the underlying factors contributing to the difficulty in number recognition can be beneficial in supporting the child's math development. Providing engaging activities, number-sense practice, and exposure to numerical concepts can help improve their number recognition skills and promote their overall math abilities."
        );
        concernsList.add(difficultyWithNumberRecognitionAgain);
        ConcernsDTO difficultyWithVocabulary1 = new ConcernsDTO(
                concernsId++,
                "vocabulary",
                "academic",
                "reading",
                "vocabulary",
                "A child experiencing difficulty with vocabulary may struggle to understand and use a wide range of words. Vocabulary knowledge plays a crucial role in reading comprehension and academic success. Addressing the underlying factors contributing to the difficulty in vocabulary can be beneficial in supporting the child's reading development. Providing explicit vocabulary instruction, context-rich activities, and exposure to a variety of texts can help improve their vocabulary skills and enhance their overall reading abilities."
        );
        concernsList.add(difficultyWithVocabulary1);

        ConcernsDTO difficultyWithWordRecognition1 = new ConcernsDTO(
                concernsId++,
                "words",
                "academic",
                "reading",
                "word recognition",
                "A child experiencing difficulty with word recognition may struggle to quickly and accurately identify familiar words. Word recognition is essential for fluent reading and comprehension. Addressing the underlying factors contributing to the difficulty in word recognition can be beneficial in supporting the child's reading development. Providing repeated exposure to high-frequency words, word recognition activities, and strategies for decoding unfamiliar words can help improve their word recognition skills and enhance their overall reading abilities."
        );
        concernsList.add(difficultyWithWordRecognition1);

        ConcernsDTO difficultyWithSoundingOutWords = new ConcernsDTO(
                concernsId++,
                "sounding out words",
                "academic",
                "reading",
                "phonics",
                "A child experiencing difficulty sounding out words during reading may struggle with phonics, which is the ability to connect sounds with letters or letter combinations. This difficulty can impact their overall reading fluency and comprehension. Addressing the underlying factors contributing to the difficulty in phonics can be beneficial in supporting the child's reading development. Providing targeted instruction, multisensory activities, and practice with phonics skills can help improve their ability to decode words and enhance their reading abilities."
        );
        concernsList.add(difficultyWithSoundingOutWords);

        ConcernsDTO difficultyWithGeneralMath = new ConcernsDTO(
                concernsId++,
                "math",
                "academic",
                "math",
                "general math",
                "A child experiencing difficulty with math may face challenges in areas such as number sense, arithmetic, algebra, geometry, and word problems. Number sense refers to understanding the relationships between numbers and their magnitude. Arithmetic skills involve basic mathematical operations such as addition, subtraction, multiplication, and division. Algebra focuses on variables, equations, and solving for unknowns. Geometry deals with shapes, spatial reasoning, and measurement. Word problems require translating real-world situations into mathematical expressions and solving them. If your child is struggling with any of these aspects of math, it may be helpful to look up resources on number sense activities, arithmetic strategies, algebraic concepts, geometry lessons, and word problem-solving techniques. Addressing these underlying factors can support your child's overall math development and enhance their mathematical abilities."
        );
        concernsList.add(difficultyWithGeneralMath);

        ConcernsDTO difficultyWithAlgebra2 = new ConcernsDTO(
                concernsId++,
                "algebra",
                "academic",
                "math",
                "algebra",
                "A child experiencing difficulty with algebra may struggle with solving equations, understanding variables, and applying algebraic concepts. Algebra is a critical branch of mathematics for problem-solving and higher-level math courses. Addressing the underlying factors contributing to the difficulty in algebra can be beneficial in supporting the child's math development. Providing step-by-step guidance, visual representations, and real-life applications of algebraic concepts can help improve their algebra skills and promote their overall math abilities."
        );
        concernsList.add(difficultyWithAlgebra2);

        ConcernsDTO difficultyWithGeometry2 = new ConcernsDTO(
                concernsId++,
                "geometry",
                "academic",
                "math",
                "geometry",
                "A child experiencing difficulty with geometry may struggle with understanding shapes, spatial relationships, and geometric concepts. Geometry plays a crucial role in visualizing and analyzing the properties of objects. Addressing the underlying factors contributing to the difficulty in geometry can be beneficial in supporting the child's math development. Providing hands-on activities, visual aids, and real-world examples can help improve their geometry skills and promote their overall math abilities."
        );
        concernsList.add(difficultyWithGeometry2);

        ConcernsDTO difficultyReading = new ConcernsDTO(
                concernsId++,
                "difficulty reading",
                "academic",
                "reading",
                "reading",
                "A child experiencing difficulty with general reading may face challenges in areas such as fluency, comprehension, letter or word recognition, and accuracy. Fluency refers to the ability to read with speed, accuracy, and expression. Comprehension involves understanding and making meaning from texts, which includes skills like vocabulary knowledge, inferencing, and understanding text structures. Letter or word recognition pertains to identifying and understanding letters, words, and their corresponding sounds. Accuracy refers to reading with precision and avoiding errors. If your child is struggling with any of these aspects of reading, it may be helpful to look up resources on fluency strategies, comprehension techniques, letter and word recognition activities, and accuracy improvement exercises. Addressing these underlying factors can support your child's overall reading development and enhance their reading abilities."
        );
        concernsList.add(difficultyReading);
        ConcernsDTO readingDifficulty = new ConcernsDTO(
                concernsId++,
                "reading",
                "academic",
                "reading",
                "reading",
                "A child experiencing difficulty with general reading may face challenges in areas such as fluency, comprehension, letter or word recognition, and accuracy. Fluency refers to the ability to read with speed, accuracy, and expression. Comprehension involves understanding and making meaning from texts, which includes skills like vocabulary knowledge, inferencing, and understanding text structures. Letter or word recognition pertains to identifying and understanding letters, words, and their corresponding sounds. Accuracy refers to reading with precision and avoiding errors. If your child is struggling with any of these aspects of reading, it may be helpful to look up resources on fluency strategies, comprehension techniques, letter and word recognition activities, and accuracy improvement exercises. Addressing these underlying factors can support your child's overall reading development and enhance their reading abilities."
        );
        concernsList.add(readingDifficulty);

        ConcernsDTO readingDifficulty2 = new ConcernsDTO(
                concernsId++,
                "reading difficulty",
                "academic",
                "reading",
                "reading",
                "A child experiencing difficulty with general reading may face challenges in areas such as fluency, comprehension, letter or word recognition, and accuracy. Fluency refers to the ability to read with speed, accuracy, and expression. Comprehension involves understanding and making meaning from texts, which includes skills like vocabulary knowledge, inferencing, and understanding text structures. Letter or word recognition pertains to identifying and understanding letters, words, and their corresponding sounds. Accuracy refers to reading with precision and avoiding errors. If your child is struggling with any of these aspects of reading, it may be helpful to look up resources on fluency strategies, comprehension techniques, letter and word recognition activities, and accuracy improvement exercises. Addressing these underlying factors can support your child's overall reading development and enhance their reading abilities."
        );
        concernsList.add(readingDifficulty2);

        ConcernsDTO difficultyWithArithmetic2 = new ConcernsDTO(
                concernsId++,
                "arithmetic",
                "academic",
                "math",
                "arithmetic",
                "A child experiencing difficulty with arithmetic may struggle with basic mathematical operations such as addition, subtraction, multiplication, and division. Arithmetic skills are foundational for more advanced math concepts. Addressing the underlying factors contributing to the difficulty in arithmetic can be beneficial in supporting the child's math development. Providing structured practice, visual aids, and real-life problem-solving opportunities can help improve their arithmetic skills and promote their overall math abilities."
        );
        concernsList.add(difficultyWithArithmetic2);

        ConcernsDTO difficultyWithAlgebra3 = new ConcernsDTO(
                concernsId++,
                "algebra",
                "academic",
                "math",
                "algebra",
                "A child experiencing difficulty with algebra may struggle with solving equations, understanding variables, and applying algebraic concepts. Algebra is a critical branch of mathematics for problem-solving and higher-level math courses. Addressing the underlying factors contributing to the difficulty in algebra can be beneficial in supporting the child's math development. Providing step-by-step guidance, visual representations, and real-life applications of algebraic concepts can help improve their algebra skills and promote their overall math abilities."
        );
        concernsList.add(difficultyWithAlgebra3);

        ConcernsDTO difficultyWithGeometry4 = new ConcernsDTO(
                concernsId++,
                "geometry",
                "academic",
                "math",
                "geometry",
                "A child experiencing difficulty with geometry may struggle with understanding shapes, spatial relationships, and geometric concepts. Geometry plays a crucial role in visualizing and analyzing the properties of objects. Addressing the underlying factors contributing to the difficulty in geometry can be beneficial in supporting the child's math development. Providing hands-on activities, visual aids, and real-world examples can help improve their geometry skills and promote their overall math abilities."
        );
        concernsList.add(difficultyWithGeometry4);

        ConcernsDTO difficultyWithLetterFormation1 = new ConcernsDTO(
                concernsId++,
                "letter formation",
                "academic",
                "writing",
                "letter formation",
                "A child experiencing difficulty with letter formation may struggle to correctly write letters in a legible and consistent manner. Letter formation is an important aspect of developing good handwriting skills. Addressing the underlying factors contributing to the difficulty in letter formation can be beneficial in supporting the child's writing development. Providing guided practice, fine motor exercises, and proper pencil grip techniques can help improve their letter formation skills and promote their overall writing abilities."
        );
        concernsList.add(difficultyWithLetterFormation1);

        ConcernsDTO difficultyWithGrammar2 = new ConcernsDTO(
                concernsId++,
                "grammar",
                "academic",
                "writing",
                "grammar",
                "A child experiencing difficulty with grammar may struggle with understanding and applying the rules of language in their writing. Grammar skills are essential for clear and effective communication. Addressing the underlying factors contributing to the difficulty in grammar can be beneficial in supporting the child's writing development. Providing explicit instruction, practice exercises, and feedback on grammar concepts can help improve their grammar skills and promote their overall writing abilities."
        );
        concernsList.add(difficultyWithGrammar2);
        ConcernsDTO spellingDifficulty = new ConcernsDTO(
                concernsId++,
                "spelling",
                "academic",
                "writing",
                "spelling",
                "A child experiencing difficulty with spelling may struggle with accurately spelling words in their writing. Spelling skills contribute to written communication and language development. Addressing the underlying factors contributing to the difficulty in spelling can be beneficial in supporting the child's writing development. Providing explicit instruction, word study activities, and opportunities for practice and reinforcement can help improve their spelling skills and promote their overall writing abilities."
        );
        concernsList.add(spellingDifficulty);

        ConcernsDTO mathDifficulty = new ConcernsDTO(
                concernsId++,
                "math difficulty",
                "academic",
                "math",
                "general math",
                "A child experiencing difficulty with math may face challenges in areas such as number sense, arithmetic, algebra, geometry, and word problems. Number sense refers to understanding the relationships between numbers and their magnitude. Arithmetic skills involve basic mathematical operations such as addition, subtraction, multiplication, and division. Algebra focuses on variables, equations, and solving for unknowns. Geometry deals with shapes, spatial reasoning, and measurement. Word problems require translating real-world situations into mathematical expressions and solving them. If your child is struggling with any of these aspects of math, it may be helpful to look up resources on number sense activities, arithmetic strategies, algebraic concepts, geometry lessons, and word problem-solving techniques. Addressing these underlying factors can support your child's overall math development and enhance their mathematical abilities."
        );
        concernsList.add(mathDifficulty);

        ConcernsDTO mathDifficulty2 = new ConcernsDTO(
                concernsId++,
                "math with difficulty",
                "academic",
                "math",
                "general math",
                "A child experiencing difficulty with math may face challenges in areas such as number sense, arithmetic, algebra, geometry, and word problems. Number sense refers to understanding the relationships between numbers and their magnitude. Arithmetic skills involve basic mathematical operations such as addition, subtraction, multiplication, and division. Algebra focuses on variables, equations, and solving for unknowns. Geometry deals with shapes, spatial reasoning, and measurement. Word problems require translating real-world situations into mathematical expressions and solving them. If your child is struggling with any of these aspects of math, it may be helpful to look up resources on number sense activities, arithmetic strategies, algebraic concepts, geometry lessons, and word problem-solving techniques. Addressing these underlying factors can support your child's overall math development and enhance their mathematical abilities."
        );
        concernsList.add(mathDifficulty2);

        ConcernsDTO readingWithDifficulty = new ConcernsDTO(
                concernsId++,
                "reading with difficulty",
                "academic",
                "reading",
                "reading",
                "A child experiencing difficulty with general reading may face challenges in areas such as fluency, comprehension, letter or word recognition, and accuracy. Fluency refers to the ability to read with speed, accuracy, and expression. Comprehension involves understanding and making meaning from texts, which includes skills like vocabulary knowledge, inferencing, and understanding text structures. Letter or word recognition pertains to identifying and understanding letters, words, and their corresponding sounds. Accuracy refers to reading with precision and avoiding errors. If your child is struggling with any of these aspects of reading, it may be helpful to look up resources on fluency strategies, comprehension techniques, letter and word recognition activities, and accuracy improvement exercises. Addressing these underlying factors can support your child's overall reading development and enhance their reading abilities."
        );
        concernsList.add(readingWithDifficulty);

        ConcernsDTO difficultyWithLetterFormation2 = new ConcernsDTO(
                concernsId++,
                "difficulty with letter formation",
                "academic",
                "writing",
                "letter formation",
                "A child experiencing difficulty with letter formation may struggle to correctly write letters in a legible and consistent manner. Letter formation is an important aspect of developing good handwriting skills. Addressing the underlying factors contributing to the difficulty in letter formation can be beneficial in supporting the child's writing development. Providing guided practice, fine motor exercises, and proper pencil grip techniques can help improve their letter formation skills and promote their overall writing abilities."
        );
        concernsList.add(difficultyWithLetterFormation2);

        ConcernsDTO grammarDifficulty = new ConcernsDTO(
                concernsId++,
                "grammar difficulty",
                "academic",
                "writing",
                "grammar",
                "A child experiencing difficulty with grammar may struggle with understanding and applying the rules of language in their writing. Grammar skills are essential for clear and effective communication. Addressing the underlying factors contributing to the difficulty in grammar can be beneficial in supporting the child's writing development. Providing explicit instruction, practice exercises, and feedback on grammar concepts can help improve their grammar skills and promote their overall writing abilities."
        );
        concernsList.add(grammarDifficulty);

        ConcernsDTO spellingDifficulty2 = new ConcernsDTO(
                concernsId++,
                "spelling difficulty",
                "academic",
                "writing",
                "spelling",
                "A child experiencing difficulty with spelling may struggle with accurately spelling words in their writing. Spelling skills contribute to written communication and language development. Addressing the underlying factors contributing to the difficulty in spelling can be beneficial in supporting the child's writing development. Providing explicit instruction, word study activities, and opportunities for practice and reinforcement can help improve their spelling skills and promote their overall writing abilities."
        );
        concernsList.add(spellingDifficulty2);
        ConcernsDTO writingFluency = new ConcernsDTO(
                concernsId++,
                "writing",
                "academic",
                "writing",
                "fluency",
                "A child experiencing difficulty with writing fluency may struggle with writing smoothly and maintaining a consistent pace. Writing fluency is important for expressing ideas effectively and coherently. Addressing the underlying factors contributing to the difficulty in fluency can be beneficial in supporting the child's writing development. Providing timed writing exercises, scaffolding techniques, and opportunities for repeated practice can help improve their writing fluency and promote their overall writing abilities."
        );
        concernsList.add(writingFluency);

        ConcernsDTO compositionDifficulty = new ConcernsDTO(
                concernsId++,
                "composition",
                "academic",
                "writing",
                "composition",
                "A child experiencing difficulty with composition may struggle with organizing ideas, structuring sentences and paragraphs, and developing a coherent piece of writing. Composition skills are crucial for effective written communication. Addressing the underlying factors contributing to the difficulty in composition can be beneficial in supporting the child's writing development. Providing writing prompts, graphic organizers, and guidance on the writing process can help improve their composition skills and promote their overall writing abilities."
        );
        concernsList.add(compositionDifficulty);

        ConcernsDTO letterFormationDifficulty2 = new ConcernsDTO(
                concernsId++,
                "letter formation difficulty",
                "academic",
                "writing",
                "letter formation",
                "A child experiencing difficulty with letter formation may struggle to correctly write letters in a legible and consistent manner. Letter formation is an important aspect of developing good handwriting skills. Addressing the underlying factors contributing to the difficulty in letter formation can be beneficial in supporting the child's writing development. Providing guided practice, fine motor exercises, and proper pencil grip techniques can help improve their letter formation skills and promote their overall writing abilities."
        );
        concernsList.add(letterFormationDifficulty2);

        ConcernsDTO writingGrammar = new ConcernsDTO(
                concernsId++,
                "writing",
                "academic",
                "writing",
                "grammar",
                "A child experiencing difficulty with grammar may struggle with understanding and applying the rules of language in their writing. Grammar skills are essential for clear and effective communication. Addressing the underlying factors contributing to the difficulty in grammar can be beneficial in supporting the child's writing development. Providing explicit instruction, practice exercises, and feedback on grammar concepts can help improve their grammar skills and promote their overall writing abilities."
        );
        concernsList.add(writingGrammar);

        ConcernsDTO writingSpelling = new ConcernsDTO(
                concernsId++,
                "writing",
                "academic",
                "writing",
                "spelling",
                "A child experiencing difficulty with spelling may struggle with accurately spelling words in their writing. Spelling skills contribute to written communication and language development. Addressing the underlying factors contributing to the difficulty in spelling can be beneficial in supporting the child's writing development. Providing explicit instruction, word study activities, and opportunities for practice and reinforcement can help improve their spelling skills and promote their overall writing abilities."
        );
        concernsList.add(writingSpelling);

        ConcernsDTO writingDifficulty = new ConcernsDTO(
                concernsId++,
                "writing difficulty",
                "academic",
                "writing",
                "fluency",
                "A child experiencing difficulty with fluency may struggle with writing smoothly and maintaining a consistent pace. Writing fluency is important for expressing ideas effectively and coherently. Addressing the underlying factors contributing to the difficulty in fluency can be beneficial in supporting the child's writing development. Providing timed writing exercises, scaffolding techniques, and opportunities for repeated practice can help improve their writing fluency and promote their overall writing abilities."
        );
        concernsList.add(writingDifficulty);

        ConcernsDTO compositionDifficulty2 = new ConcernsDTO(
                concernsId++,
                "composition difficulty",
                "academic",
                "writing",
                "composition",
                "A child experiencing difficulty with composition may struggle with organizing ideas, structuring sentences and paragraphs, and developing a coherent piece of writing. Composition skills are crucial for effective written communication. Addressing the underlying factors contributing to the difficulty in composition can be beneficial in supporting the child's writing development. Providing writing prompts, graphic organizers, and guidance on the writing process can help improve their composition skills and promote their overall writing abilities."
        );
        concernsList.add(compositionDifficulty2);

    }

}

