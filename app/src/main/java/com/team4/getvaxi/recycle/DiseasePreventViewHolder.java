package com.team4.getvaxi.recycle;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.R;

;

public class DiseasePreventViewHolder extends RecyclerView.ViewHolder {

  private final TextView vaccineByAgeVName;

  private String disease;
  private DiseasePreventAdapter adapter;

  public DiseasePreventViewHolder(@NonNull View layoutView) {
    super(layoutView);

    vaccineByAgeVName = layoutView.findViewById(R.id.custom_vaccinebyage_vaccinename);


//    layoutView.setOnClickListener(
//        v -> {
//          Intent i = new Intent(layoutView.getContext(), BookVaccineActivity.class);
//          Bundle b = new Bundle();
//          b.putSerializable("vaccineDetails", (Serializable) vaccine);
//          i.putExtras(b);
//          // i.putExtra("vaccineName", vaccine.getVaccineName());
//
//          layoutView.getContext().startActivity(i);
//        });
  }

  public void bind(String disease, DiseasePreventAdapter adapter) {
    this.disease = disease;
    this.adapter = adapter;
   vaccineByAgeVName.setText(disease);


  }
}
