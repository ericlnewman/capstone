package com.ericnewman.sfaapp.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.ericnewman.sfaapp.R;
import com.ericnewman.sfaapp.dto.ConcernsDTO;
import java.util.List;
public class ConcernsAdapter extends ArrayAdapter<ConcernsDTO>
{

    public ConcernsAdapter(Context context, int resource, List<ConcernsDTO> shapeList){
        super(context,resource,shapeList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ConcernsDTO concernsDTO = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_cell, parent, false);
        }
        TextView tv =  convertView.findViewById(R.id.concernName);
        TextView tv2 = convertView.findViewById(R.id.concernDescription);

        tv.setText(concernsDTO.getConcernsName());
        tv2.setText(concernsDTO.getConcernDescription());


        return convertView;
    }
}
