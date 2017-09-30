package com.isabverma.letscode.startLearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.isabverma.letscode.R;
import com.isabverma.letscode.modal.Flavour;
import com.isabverma.letscode.modal.Topic;

import java.util.ArrayList;

/**
 * Created by isabverma on 3/7/2017.
 */

public class TopicExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<Flavour> flavourList;
    private ArrayList<Flavour> flavourOriginalList;

    public TopicExpandableListAdapter(Context context, ArrayList<Flavour> flavourList){
        this.context = context;
        this.flavourList = new ArrayList<Flavour>();
        this.flavourList.addAll(flavourList);
        this.flavourOriginalList = new ArrayList<Flavour>();
        this.flavourOriginalList.addAll(flavourList);
    }

    @Override
    public int getGroupCount() {
        return flavourList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Topic> topicList = flavourList.get(groupPosition).getTopicList();
        return topicList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return flavourList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Topic> topicList = flavourList.get(groupPosition).getTopicList();
        return topicList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Flavour flavour = (Flavour) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.flavour_row, null);
        }
        TextView flavourName = (TextView) convertView.findViewById(R.id.flavour_row_text_view);
        flavourName.setText(flavour.getFlavourName().trim());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Topic topic = (Topic) getChild(groupPosition, childPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.topic_row, null);
        }
        TextView topicNumber = (TextView) convertView.findViewById(R.id.topic_row_number_text_view);
        topicNumber.setText(Long.toString(topic.getTopicNumber()));
        TextView topicName = (TextView) convertView.findViewById(R.id.topic_row_name_text_view);
        topicName.setText(topic.getTopicName().trim());
        TextView topicDescription = (TextView) convertView.findViewById(R.id.topic_row_description_text_view);
        topicDescription.setText(topic.getTopicDescription().trim());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    public void filterData(String query){
        query = query.toLowerCase();
        flavourList.clear();
        if(query.isEmpty()){
            flavourList.addAll(flavourOriginalList);
        }else{
            for(Flavour flavour : flavourOriginalList){
                ArrayList<Topic> topicList = flavour.getTopicList();
                ArrayList<Topic> newList = new ArrayList<Topic>();
                for(Topic topic : topicList){
                    if(topic.getTopicName().toLowerCase().contains(query) || topic.getTopicDescription().toLowerCase().contains(query)){
                        newList.add(topic);
                    }
                }
                if(newList.size() > 0){
                    Flavour nFlavour = new Flavour(flavour.getFlavourName(), newList);
                    flavourList.add(nFlavour);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void updateExpendableList(ArrayList<Flavour> flavourList) {
        this.flavourOriginalList = flavourList;
        notifyDataSetChanged();
        filterData("");
    }
}
