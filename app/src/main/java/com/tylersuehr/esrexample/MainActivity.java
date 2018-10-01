package com.tylersuehr.esrexample;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.tylersuehr.esr.ContentItemLoadingStateFactory;
import com.tylersuehr.esr.EmptyStateRecyclerView;
import com.tylersuehr.esr.TextStateDisplay;

/**
 * Copyright © 2017 Tyler Suehr
 *
 * @author Tyler Suehr
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmptyStateRecyclerView rv = (EmptyStateRecyclerView)findViewById(R.id.recycler);
        // setup any states here...

        rv.setStateDisplay(EmptyStateRecyclerView.STATE_LOADING,
                ContentItemLoadingStateFactory.newListLoadingState(this));
        rv.setStateDisplay(EmptyStateRecyclerView.STATE_EMPTY,
                new TextStateDisplay(this, "No content yet", "Please join or create some content."));
        rv.setStateDisplay(EmptyStateRecyclerView.STATE_ERROR,
                new TextStateDisplay(this, "SORRY...!", "Something went wrong :("));

        pretendRunLongTask(rv);
    }

    private void pretendRunLongTask(final EmptyStateRecyclerView rv) {
        rv.invokeState(EmptyStateRecyclerView.STATE_LOADING);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rv.invokeState(EmptyStateRecyclerView.STATE_ERROR);
            }
        }, 5000);
    }
}