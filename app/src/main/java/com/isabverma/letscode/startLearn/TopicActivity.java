package com.isabverma.letscode.startLearn;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isabverma.letscode.R;
import com.isabverma.letscode.modal.Flavour;
import com.isabverma.letscode.modal.Topic;

import java.util.ArrayList;

public class TopicActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    String productName = "";

    private DatabaseReference databaseReference;

    private SearchView search;
    private TopicExpandableListAdapter topicExpandableListAdapter;
    private ExpandableListView expandableListView;
    private ArrayList<Flavour> flavoutList = new ArrayList<Flavour>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        productName = getIntent().getStringExtra("productName");
        this.setTitle("Learn " + productName);

        //writeData(); //Required for data load

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.activity_topic_search_view);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        displayList();
        expandAll();
    }

    @Override
    public boolean onClose() {
        topicExpandableListAdapter.filterData("");
        expandAll();
        return false;
    }

    private void expandAll(){
        int count = topicExpandableListAdapter.getGroupCount();
        for(int i=0;i<count;i++){
            expandableListView.expandGroup(i);
        }
    }

    private void displayList(){
        loadSomeData();
        expandableListView = (ExpandableListView) findViewById(R.id.activity_topic_expendable_listview);
        topicExpandableListAdapter = new TopicExpandableListAdapter(TopicActivity.this, flavoutList);
        expandableListView.setAdapter(topicExpandableListAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        topicExpandableListAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        topicExpandableListAdapter.filterData(newText);
        expandAll();
        return false;
    }

    private void writeData(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        loadSomeDataForOnce();
        databaseReference.child(productName).setValue(flavoutList);
    }

    private void loadSomeDataForOnce(){
        ArrayList<Topic> topicList = new ArrayList<Topic>();
        Topic topic = new Topic(1l, "Sample Topic", "This chapter five you basics idea of what HTML is.. It will also tell what you need to have knowledge to start with that");
        topicList.add(topic);
        topic = new Topic(2l, "Introduction", "This chapter five you basics idea of what HTML is..");
        topicList.add(topic);
        topic = new Topic(3l, "Introduction", "This chapter five you basics idea of what HTML is..");
        topicList.add(topic);

        Flavour flavour = new Flavour("Basic", topicList);
        flavoutList.add(flavour);

        topicList = new ArrayList<Topic>();
        topic = new Topic(2l, "Introduction", "This chapter five you basics idea of what HTML is..");
        topicList.add(topic);
        topic = new Topic(3l, "Introduction", "This chapter five you basics idea of what HTML is..");
        topicList.add(topic);

        flavour = new Flavour("Proficient", topicList);
        flavoutList.add(flavour);

        topicList = new ArrayList<Topic>();
        topic = new Topic(2l, "Introduction", "This chapter five you basics idea of what HTML is..");
        topicList.add(topic);
        topic = new Topic(3l, "Introduction", "This chapter five you basics idea of what HTML is..");
        topicList.add(topic);

        flavour = new Flavour("Expert", topicList);
        flavoutList.add(flavour);
    }
    private void loadSomeData(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final ArrayList<Flavour> flavourArrayList = new ArrayList<Flavour>();
        final ProgressDialog progressDialog = new ProgressDialog(TopicActivity.this);
        progressDialog.setMessage("Please wait.. Loading data...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        databaseReference.child(productName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                flavourArrayList.clear();
                for (DataSnapshot child : children) {
                    Flavour flavour = child.getValue(Flavour.class);
                    flavourArrayList.add(flavour);
                }
                flavoutList.clear();
                flavoutList.addAll(flavourArrayList);
                topicExpandableListAdapter.updateExpendableList(flavoutList);
                expandAll();
                progressDialog.hide();
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(TopicActivity.this, "OOPS..!!! Connection to server cannot be established.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
