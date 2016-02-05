package com.dukhabar.youthutsav;

/**
 * Created by sysAdmin on 05/02/16.
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.truizlop.fabreveallayout.FABRevealLayout;
import com.truizlop.fabreveallayout.OnRevealChangeListener;

import java.net.URLEncoder;


/**
 * Created by RajanMaurya on 16/10/15.
 */
public class Android_Team extends Fragment {

    private final String LOG_TAG = getClass().getSimpleName();
    private int Position;
    private String[]   developer_name , developer_image , developer , developer_mobile , developer_email , github , fbprofile;
    private TextView developer_name_tv , developer_work;


    public Android_Team(int Position)
    {
        this.Position = Position;
    }

    public Android_Team()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        developer_name = getResources().getStringArray(R.array.developer_name);
        developer_image = getResources().getStringArray(R.array.developer_image);
        developer = getResources().getStringArray(R.array.developerhome);
        developer_mobile = getResources().getStringArray(R.array.developer_mobileno);
        developer_email = getResources().getStringArray(R.array.developer_email);
        github = getResources().getStringArray(R.array.github);
        fbprofile = getResources().getStringArray(R.array.fbprofileid);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.android_team, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(LOG_TAG, Position + "");



        /**
         * Setting member name dynamically
         */
        developer_name_tv = (TextView)view.findViewById(R.id.name);
        developer_name_tv.setText(developer_name[Position]);


        /**
         * Setting android work
         */

        developer_work = (TextView)view.findViewById(R.id.android_work);
        developer_work.setText(developer[Position]);


        /**
         *  checking Developer Contact is present in user phone if yes then open contact call
         *  else saving contact to user phone .
         */
        view.findViewById(R.id.contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (false) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse(developer_mobile[Position]));
                    startActivity(callIntent);

                } else {
                    addtocontact(getActivity(), Position);
                }

            }
        });


        /**
         * Opening Gmail Intent on click button
         */
        view.findViewById(R.id.email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String details="mailto: " + developer_email[Position] + "?subject=" + URLEncoder.encode("TFI App");
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(details));
                startActivity(intent);
            }
        });

        /**
         * Opening facebook profile onclick button
         */
        view.findViewById(R.id.facebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent rate = new Intent(android.content.Intent.ACTION_VIEW);
                rate.setData(Uri.parse(fbprofile[Position]));
                startActivity(rate);

            }
        });

        /**
         * Opening Github onclick button
         */
        view.findViewById(R.id.github).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent rate = new Intent(android.content.Intent.ACTION_VIEW);
                rate.setData(Uri.parse(github[Position]));
                startActivity(rate);

            }
        });


        /**
         * Setting Profile image
         */
        final ImageView profile_image = (ImageView) view.findViewById(R.id.profile_image);

        Picasso.with(getActivity()).load(developer_image[Position]).into(profile_image);

        FABRevealLayout fabRevealLayout = (FABRevealLayout) view.findViewById(R.id.fab_reveal_layout);
        configureFABReveal(fabRevealLayout);

    }


    private void configureFABReveal(FABRevealLayout fabRevealLayout) {
        fabRevealLayout.setOnRevealChangeListener(new OnRevealChangeListener() {
            @Override
            public void onMainViewAppeared(FABRevealLayout fabRevealLayout, View mainView) {
            }

            @Override
            public void onSecondaryViewAppeared(final FABRevealLayout fabRevealLayout, View secondaryView) {
                prepareBackTransition(fabRevealLayout);
            }
        });
    }

    private void prepareBackTransition(final FABRevealLayout fabRevealLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fabRevealLayout.revealMainView();

            }
        }, 10000);
    }


    /**
     * Adding Contact In User Contact
     * @param context
     * @param Position viewpager Position
     */
    public void addtocontact(final Context context , int Position) {

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        intent.putExtra(ContactsContract.Intents.Insert.NAME, developer_name[Position]);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, developer_mobile[Position]);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, developer_email[Position]);

        context.startActivity(intent);

    }

    /**
     * Checking Contact in user data
     * @param context
     * @param number developer number
     * @return
     */
    public boolean contactExists(Context context, String number) {
        Uri lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] mPhoneNumberProjection = { ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER, ContactsContract.PhoneLookup.DISPLAY_NAME };
        Cursor cur = context.getContentResolver().query(lookupUri,mPhoneNumberProjection, null, null, null);
        try {
            if (cur.moveToFirst()) {
                return true;
            }
        } finally {
            if (cur != null)
                cur.close();
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}