package com.evolutiondso.www.contactlisttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.evolutiondso.www.contactlisttest.Model.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Detail extends AppCompatActivity {

    //ButterKnife Binds
    @BindView(R.id.user_profile_photo)
    public ImageButton imgBTN_profile_Photo;
    @BindView(R.id.user_profile_company)
    public TextView tv_profile_company;
    @BindView(R.id.user_profile_adress)
    public TextView tv_profile_adress;
    @BindView(R.id.user_profile_bday)
    public TextView tv_profile_bday;
    @BindView(R.id.user_profile_email)
    public TextView tv_profile_email;
    @BindView(R.id.user_profile_name)
    public TextView tv_profile_name;
    @BindView(R.id.user_profile_phone)
    public TextView tv_profile_phone;
    @BindView(R.id.header_cover_image)
    public ImageView IMGV_cover;


    public Result persons;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);


        persons = getIntent().getParcelableExtra("RESULT"); //if it's a string you stored.
        id = getIntent().getIntExtra("POSITION",0);

        Glide.with(this)
                .load(persons.getSmallImageURL())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgBTN_profile_Photo);

        tv_profile_adress.setText(persons.getAddress().toString());
        tv_profile_name.setText(persons.getName().toString());
        tv_profile_phone.setText(persons.getPhone().toString());
        tv_profile_email.setText(persons.getEmail().toString());
        tv_profile_company.setText(persons.getCompany().toString());
        tv_profile_bday.setText(persons.getWebsite());

        Glide.with(this)
                .load(persons.getLargeImageURL())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(IMGV_cover);





    }
}
