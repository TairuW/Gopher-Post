package com.example.gopherpost.ui.make_post;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.gopherpost.Event;
import com.example.gopherpost.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import io.noties.markwon.Markwon;
import io.noties.markwon.editor.MarkwonEditor;
import io.noties.markwon.editor.MarkwonEditorTextWatcher;

public class MakePostFragment extends Fragment {

    private MakePostViewModel makePostViewModel;
    public  Event createdEvent;
    private GregorianCalendar startCal;
    private GregorianCalendar endCal;
    private ImageView image;

    //todo: make each of these changing update the Event ^^
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        makePostViewModel =
                ViewModelProviders.of(this).get(MakePostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_make_post, container, false);

        resetPage(root);

        return root;
    }

    private void resetPage(final View root){
        createdEvent = new Event();
        startCal = createdEvent.getStartCal();
        endCal = createdEvent.getEndCal();
        createdEvent.setFinished(false);
        final Markwon markwon = Markwon.create(root.getContext());
        final MarkwonEditor editor = MarkwonEditor.create(markwon);


        // title
        final TextView title = root.findViewById(R.id.text_title);
        title.setSelectAllOnFocus(true);
        title.setText("Edit Title");
        title.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    // just lost focus; write title
                    createdEvent.setTitle(title.getText().toString());
                }
            }
        });

        // image
        image = root.findViewById(R.id.imageView2);
        final Button btnChangeImage = root.findViewById(R.id.button9);
        image.setImageResource(R.drawable.logo);  // default is our logo
        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1972); //request code is just an int so we can tell which start activity a result comes from if we start more than one.
            }
        });


        // description
        final TextView tvDescription = root.findViewById(R.id.multiTextView);
        tvDescription.setText("");
        // obtain an instance of Markwon
        // set markdown // this will be what we do for the post display page
        // markwon.setMarkdown(tvDescription, "**Hello there!**");
        // set edit text listener
        tvDescription.addTextChangedListener(MarkwonEditorTextWatcher.withProcess(editor));
        tvDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    // just lost focus; save current description.
                    createdEvent.setLongdesc(tvDescription.getText().toString());

                    final Spanned spanned = markwon.toMarkdown(tvDescription.getText().toString());
                    createdEvent.setShortdesc(spanned.toString());  // this strips markdown formatting for short description
                }
            }
        });

        // tags
        final EditText textTags = root.findViewById(R.id.editText6);
        textTags.setText("[Event]");
        textTags.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    // just lost focus; write changes to event
                    //createdEvent
                    textTags.getText().toString().split("\\s+");
                }
            }
        });


        // location
        final ToggleButton toggleLocation = root.findViewById(R.id.toggleButton);
        final EditText textLocation = root.findViewById(R.id.edit_location);
        textLocation.setText("Select Location");
        textLocation.setSelectAllOnFocus(true);
        toggleLocation.setChecked(false);
        //when user selects the location text box, the toggle for location should become enabled
        textLocation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                toggleLocation.setChecked(true);

                if(!hasFocus){
                    // we just lost focus; write new location to event
                    createdEvent.setLocation(textLocation.getText().toString());
                }
            }
        });
        toggleLocation.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(!checked){
                    createdEvent.setLocation("");
                } else {
                    createdEvent.setLocation(textLocation.getText().toString());
                }
            }
        });


        // date/time for start and stop of this event or post
        final Button btnStartDate = root.findViewById(R.id.button);
        final Button btnStartTime = root.findViewById(R.id.button7);
        final Button btnEndDate = root.findViewById(R.id.button8);
        final Button btnEndtTime = root.findViewById(R.id.button2);

        btnStartDate.setText("Today");
        btnStartTime.setText("Now");
        btnEndDate.setText("Today");
        btnEndtTime.setText("Now");

        // start date
        final DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                startCal.set(Calendar.YEAR, year);
                startCal.set(Calendar.MONTH, monthOfYear);
                startCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                btnStartDate.setText(monthOfYear + "/" + dayOfMonth + "/" + year);
            }   };
        btnStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), startDateListener, startCal.get(Calendar.YEAR),
                        startCal.get(Calendar.MONTH), startCal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // end date
        final DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                endCal.set(Calendar.YEAR, year);
                endCal.set(Calendar.MONTH, monthOfYear);
                endCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                btnEndDate.setText(monthOfYear + "/" + dayOfMonth + "/" + year);
            }   };
        btnEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), endDateListener, endCal.get(Calendar.YEAR),
                        endCal.get(Calendar.MONTH), endCal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // start time
        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String timeOfDay = "AM";
                        if(selectedHour > 12){
                            selectedHour -= 12;
                            timeOfDay = "PM";
                        }
                        btnStartTime.setText( selectedHour + ":" + selectedMinute + " " + timeOfDay);
                    }
                }, startCal.get(GregorianCalendar.HOUR), startCal.get(GregorianCalendar.MINUTE), false); // 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        // end time
        btnEndtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String timeOfDay = "AM";
                        if(selectedHour > 12){
                            selectedHour -= 12;
                            timeOfDay = "PM";
                        }
                        btnEndtTime.setText( selectedHour + ":" + selectedMinute + " " + timeOfDay);
                    }                }, endCal.get(GregorianCalendar.HOUR), endCal.get(GregorianCalendar.MINUTE), false); // No 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        // contact address
        final EditText textContact = root.findViewById(R.id.editText5);
        //todo: prefill with current logged in user's email (for demo just hardcode?)
        textContact.setText("examp007@umn.edu");
        textContact.setSelectAllOnFocus(true);
        textContact.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    // we just lost focus; save this
                    createdEvent.setContactEmail(textContact.getText().toString());
                }
            }
        });

        // submit button
        final Button btnSubmit = root.findViewById(R.id.button3);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                createdEvent.setFinished(true);
                Snackbar.make(v, R.string.post_submit_confirmation, Snackbar.LENGTH_SHORT).show(); // todo: maybe make an undo button here
                resetPage(root);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1972) { // 1972 is what we have above when we start the intent
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), contentURI);
                    createdEvent.setImage(bitmap);
                    Snackbar.make(this.getView(), "Image Saved!", Snackbar.LENGTH_SHORT).show();
                    image.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Snackbar.make(this.getView(), "Failed!", Snackbar.LENGTH_SHORT).show();
                }
            }
        }
    }

}
