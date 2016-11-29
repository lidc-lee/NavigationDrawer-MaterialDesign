package br.liveo.ndrawer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.model.HelpLiveo;
import br.liveo.navigationliveo.NavigationLiveo;
import br.liveo.ndrawer.R;
import br.liveo.ndrawer.ui.fragment.MainFragment;
import br.liveo.ndrawer.ui.fragment.ViewPagerFragment;

/**
 * Created by AA on 2016/11/29.
 */

public class SecondActivity extends NavigationLiveo implements OnItemClickListener {

    private HelpLiveo mHelpLiveo;

    @Override
    public void onInt(Bundle savedInstanceState) {
        // User Information
        this.userName.setText("Li Dongchan");
        this.userEmail.setText("1499117534@qq.com");
        this.userPhoto.setImageResource(R.drawable.ic_user);
        this.userBackground.setImageResource(R.drawable.ic_user_background_second);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.inbox), R.drawable.ic_inbox_black_24dp, 6);
        mHelpLiveo.addSubHeader(getString(R.string.categories)); //Item subHeader
        mHelpLiveo.add(getString(R.string.starred), R.drawable.ic_star_black_24dp);
        mHelpLiveo.add(getString(R.string.sent_mail), R.drawable.ic_send_black_24dp);
        mHelpLiveo.addNoCheck(getString(R.string.drafts), R.drawable.ic_drafts_black_24dp);
        mHelpLiveo.addSeparator(); //Item separator
        mHelpLiveo.add(getString(R.string.trash), R.drawable.ic_delete_black_24dp);
        mHelpLiveo.add(getString(R.string.spam), R.drawable.ic_report_black_24dp, 120);

        //{optional} - 自定义头视图
//        View mCustomHeader = getLayoutInflater().inflate(R.layout.custom_header_user, this.getListView(), false);
//        ImageView imageView = (ImageView) mCustomHeader.findViewById(R.id.imageView);

        with(this).startingPosition(2) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())
                //{optional} - List Customization "If you remove these methods and the list will take his white standard color"
                .selectorCheck(R.drawable.selector_check) //Inform the background of the selected item color
                .colorItemDefault(R.color.nliveo_blue_colorPrimary) //Inform the standard color name, icon and counter
                .colorItemSelected(R.color.nliveo_blue_colorPrimary) //State the name of the color, icon and meter when it is selected
                .backgroundList(R.color.nliveo_black_light) //Inform the list of background color
                .colorLineSeparator(R.color.nliveo_transparent) //Inform the color of the subheader line

                //{optional} - SubHeader Customization
                .colorNameSubHeader(R.color.nliveo_blue_colorPrimary)
                .colorLineSeparator(R.color.nliveo_green_colorPrimaryDark)

                //.removeFooter()
                .footerItem(R.string.settings, R.drawable.ic_settings_black_24dp)

                //{optional} - Second footer
//                .footerSecondItem(R.string.settings, R.drawable.ic_settings_black_24dp)

                //{optional} - Header Customization
//                .customHeader(mCustomHeader)

                //{optional} - Footer Customization
                .footerNameColor(R.color.nliveo_blue_colorPrimary)
                .footerIconColor(R.color.nliveo_blue_colorPrimary)

//                .footerSecondNameColor(R.color.nliveo_blue_colorPrimary)
//                .footerSecondIconColor(R.color.nliveo_blue_colorPrimary)

                .footerBackground(R.color.nliveo_white)

                //{optional} - Remove color filter icon
//                .removeColorFilter()

                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)

                //{optional} - Second footer
//                .setOnClickFooterSecond(onClickFooter)
                .build();

        int position = this.getCurrentPosition();
        this.setElevationToolBar(position != 2 ? 15 : 0);
    }

    @Override
    public void onItemClick(int position) {
        Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        switch (position){
            case 2:
                mFragment = new ViewPagerFragment();
                break;

            default:
                mFragment = MainFragment.newInstance(mHelpLiveo.get(position).getName());
                break;
        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }

        setElevationToolBar(position != 2 ? 15 : 0);
    }

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "onClickPhoto :D", Toast.LENGTH_SHORT).show();
            closeDrawer();
        }
    };
    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
            Toast.makeText(getApplicationContext(), "OnPrepareOptionsMenuLiveo :D", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "onClickFooter :D", Toast.LENGTH_SHORT).show();
            closeDrawer();
        }
    };

}
