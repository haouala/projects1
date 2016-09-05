/*
 * Copyright (c) 2012-2016 Arne Schwabe
 * Distributed under the GNU GPL v2 with additional terms. For full terms see the file doc/LICENSE.txt
 */

package de.blinkt.openvpn.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Arrays;

import de.blinkt.openvpn.R;
import de.blinkt.openvpn.VpnProfile;
import de.blinkt.openvpn.core.Connection;

public class ConnectionsAdapter extends RecyclerView.Adapter<ConnectionsAdapter.ConnectionsHolder> {
    private final Context mContext;
    private final VpnProfile mProfile;
    private final Settings_Connections mConnectionFragment;
    private Connection[] mConnections;

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER = TYPE_NORMAL + 1;

    ConnectionsAdapter(Context c, Settings_Connections connections_fragments, VpnProfile vpnProfile) {
        mContext = c;
        mConnections = vpnProfile.mConnections;
        mProfile = vpnProfile;
        mConnectionFragment = connections_fragments;
    }

    class ConnectionsHolder extends RecyclerView.ViewHolder {
        private final EditText mServerNameView;
        private final EditText mPortNumberView;
        private final TextView mServerCountryName;
        private final Switch mRemoteSwitch;
        private final RadioGroup mProtoGroup;
        private final EditText mCustomOptionText;
        private final CheckBox mCustomOptionCB;
        private final View mCustomOptionsLayout;
        private final ImageButton mDeleteButton;
        private final ImageView mServerCountryIcon;
        private final ImageView mServerCountryIconFrance;
        private final ImageView mServerCountryIconBelgium;
        private final ImageView mServerCountryIconAustralia;
        private final ImageView mServerCountryIconItaly;
        private final ImageView mServerCountryIconCanada;
        private final ImageView mServerCountryIconCzechRepublic;
        private final ImageView mServerCountryIconFinland;
        private final ImageView mServerCountryIconGermany;
        private final ImageView mServerCountryIconIndia;
        private final ImageView mServerCountryIconIreland;
        private final ImageView mServerCountryIconIsrael;
        private final ImageView mServerCountryIconJapan;
        private final ImageView mServerCountryIconLithuania;
        private final ImageView mServerCountryIconNetherlands;
        private final ImageView mServerCountryIconPoland;
        private final ImageView mServerCountryIconPortugal;
        private final ImageView mServerCountryIconRomania;
        private final ImageView mServerCountryIconSouthAfrica;
        private final ImageView mServerCountryIconSpain;
        private final ImageView mServerCountryIconSwitzerland;
        private final ImageView mServerCountryIconUnitedKingdom;
        private final ImageView mServerCountryIconUSA;





        private final EditText mConnectText;
        private final SeekBar mConnectSlider;
        private final ConnectionsAdapter mConnectionsAdapter;
        private Connection mConnection; // Set to null on update


        ConnectionsHolder(View card, ConnectionsAdapter connectionsAdapter, int viewType) {
            super(card);
            mServerNameView = (EditText) card.findViewById(R.id.servername);
            mPortNumberView = (EditText) card.findViewById(R.id.portnumber);
            mServerCountryName = (TextView) card.findViewById(R.id.server_country_name);
            mServerCountryIcon = (ImageView) card.findViewById(R.id.server_country_icon);

            mServerCountryIconFrance = (ImageView) card.findViewById(R.id.server_country_icon_france);

            mServerCountryIconBelgium = (ImageView) card.findViewById(R.id.server_country_icon_belgium);
            mServerCountryIconAustralia = (ImageView) card.findViewById(R.id.server_country_icon_australia);
            mServerCountryIconItaly = (ImageView) card.findViewById(R.id.server_country_icon_italy);
            mServerCountryIconCanada = (ImageView) card.findViewById(R.id.server_country_icon_canada);
            mServerCountryIconCzechRepublic = (ImageView) card.findViewById(R.id.server_country_icon_czech_republic);
            mServerCountryIconFinland = (ImageView) card.findViewById(R.id.server_country_icon_finland);
            mServerCountryIconGermany = (ImageView) card.findViewById(R.id.server_country_icon_germany);
            mServerCountryIconIndia = (ImageView) card.findViewById(R.id.server_country_icon_india);
            mServerCountryIconIreland = (ImageView) card.findViewById(R.id.server_country_icon_ireland);
            mServerCountryIconIsrael = (ImageView) card.findViewById(R.id.server_country_icon_israel);
            mServerCountryIconJapan = (ImageView) card.findViewById(R.id.server_country_icon_japan);
            mServerCountryIconLithuania = (ImageView) card.findViewById(R.id.server_country_icon_lithuania);
            mServerCountryIconNetherlands = (ImageView) card.findViewById(R.id.server_country_icon_netherlands);
            mServerCountryIconPoland = (ImageView) card.findViewById(R.id.server_country_icon_poland);
            mServerCountryIconPortugal = (ImageView) card.findViewById(R.id.server_country_icon_portugal);
            mServerCountryIconRomania = (ImageView) card.findViewById(R.id.server_country_icon_romania);
            mServerCountryIconSouthAfrica = (ImageView) card.findViewById(R.id.server_country_icon_south_africa);
            mServerCountryIconSpain = (ImageView) card.findViewById(R.id.server_country_icon_spain);
            mServerCountryIconSwitzerland = (ImageView) card.findViewById(R.id.server_country_icon_switzerland);
            mServerCountryIconUnitedKingdom= (ImageView) card.findViewById(R.id.server_country_icon_united_kingdom);
            mServerCountryIconUSA= (ImageView) card.findViewById(R.id.server_country_icon_usa);
            mRemoteSwitch = (Switch) card.findViewById(R.id.remoteSwitch);
            mCustomOptionCB = (CheckBox) card.findViewById(R.id.use_customoptions);
            mCustomOptionText = (EditText) card.findViewById(R.id.customoptions);
            mProtoGroup = (RadioGroup) card.findViewById(R.id.udptcpradiogroup);
            mCustomOptionsLayout = card.findViewById(R.id.custom_options_layout);
            mDeleteButton = (ImageButton) card.findViewById(R.id.remove_connection);
            mConnectSlider = (SeekBar) card.findViewById(R.id.connect_silder);
            mConnectText = (EditText) card.findViewById(R.id.connect_timeout);


            mConnectionsAdapter = connectionsAdapter;

            if (viewType == TYPE_NORMAL)
                addListeners();


        }



        void addListeners() {
            mRemoteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (mConnection != null) {
                        mConnection.mEnabled = isChecked;
                        mConnectionsAdapter.displayWarningIfNoneEnabled();
                    }
                }
            });

            mProtoGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (mConnection != null) {
                        if (checkedId == R.id.udp_proto)
                            mConnection.mUseUdp = true;
                        else if (checkedId == R.id.tcp_proto)
                            mConnection.mUseUdp = false;
                    }
                }
            });

            mCustomOptionText.addTextChangedListener(new OnTextChangedWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (mConnection != null)
                        mConnection.mCustomConfiguration = s.toString();
                }
            });

            mCustomOptionCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (mConnection != null) {
                        mConnection.mUseCustomConfig = isChecked;
                        mCustomOptionsLayout.setVisibility(mConnection.mUseCustomConfig ? View.VISIBLE : View.GONE);
                    }
                }
            });


            mServerNameView.addTextChangedListener(new OnTextChangedWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (mConnection != null) {
                        mConnection.mServerName = s.toString();
                    }


                    if(mServerNameView.getText().toString().equals("45.32.246.102"))
                    {
                        mServerCountryName.setText("Australia");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.australia_icon);
                        mServerCountryIcon.setImageResource(R.drawable.australia_icon);

                      //  mServerCountryIcon.setImageURI("@drawable/belgium_icon.png");
                    }

                    else  if(mServerNameView.getText().toString().equals("5.135.206.20"))
                    {
                        mServerCountryName.setText("Belgium 1");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.belgium_icon);
                        mServerCountryIcon.setImageResource(R.drawable.belgium_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("5.135.206.21"))
                    {
                        mServerCountryName.setText("Belgium 2");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.belgium_icon);
                        mServerCountryIcon.setImageResource(R.drawable.belgium_icon);
                    }
                    else  if(mServerNameView.getText().toString().equals("149.56.12.45"))
                    {
                        mServerCountryName.setText("Canada");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.canada_icon);
                        mServerCountryIcon.setImageResource(R.drawable.canada_icon);
                    }
                    else  if(mServerNameView.getText().toString().equals("94.23.174.196"))
                    {
                        mServerCountryName.setText("Czech Republic");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.czech_republic_icon);
                        mServerCountryIcon.setImageResource(R.drawable.czech_republic_icon);
                    }
                    else  if(mServerNameView.getText().toString().equals("5.135.72.190"))
                    {
                        mServerCountryName.setText("Finland");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.finland_icon);
                        mServerCountryIcon.setImageResource(R.drawable.finland_icon);
                    }
                    else  if(mServerNameView.getText().toString().equals("51.254.51.52"))
                    {
                        mServerCountryName.setText("France 1");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.france_icon);
                        mServerCountryIcon.setImageResource(R.drawable.france_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("51.254.51.53"))
                    {
                        mServerCountryName.setText("France 2");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.france_icon);
                        mServerCountryIcon.setImageResource(R.drawable.france_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("51.254.51.54"))
                    {
                        mServerCountryName.setText("France 3");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.france_icon);
                        mServerCountryIcon.setImageResource(R.drawable.france_icon);

                        /*  Uri imgUri= Uri.parse("android.resource://de.blinkt.openvpn.fragments/belgium_icon.png");
                        mServerCountryIcon.setImageURI(imgUri);*/

                    }
                    else if(mServerNameView.getText().toString().equals("5.196.110.198"))
                    {
                        mServerCountryName.setText("Germany");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.germany_icon);
                        mServerCountryIcon.setImageResource(R.drawable.germany_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("109.73.164.116"))
                    {
                        mServerCountryName.setText("India");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.india_icon);
                        mServerCountryIcon.setImageResource(R.drawable.india_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("188.165.6.183"))
                    {
                        mServerCountryName.setText("Ireland");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.ireland_icon);
                        mServerCountryIcon.setImageResource(R.drawable.ireland_icon);
                        /* mServerCountryIconAustralia.setVisibility(1);
                        mServerCountryIconBelgium.setVisibility(1);
                        mServerCountryIconFrance.setVisibility(1);
                        mServerCountryIconCanada.setVisibility(1);
                        mServerCountryIconCzechRepublic.setVisibility(1);
                        mServerCountryIconFinland.setVisibility(1);
                        mServerCountryIconGermany.setVisibility(1);
                        mServerCountryIconIndia.setVisibility(1);
                        mServerCountryIconIreland.setVisibility(0);
                        mServerCountryIconIsrael.setVisibility(1);
                        mServerCountryIconItaly.setVisibility(1);
                        mServerCountryIconJapan.setVisibility(1);
                        mServerCountryIconLithuania.setVisibility(1);
                        mServerCountryIconNetherlands.setVisibility(1);
                        mServerCountryIconPoland.setVisibility(1);
                        mServerCountryIconPortugal.setVisibility(1);
                        mServerCountryIconRomania.setVisibility(1);
                        mServerCountryIconSouthAfrica.setVisibility(1);
                        mServerCountryIconSpain.setVisibility(1);
                        mServerCountryIconSwitzerland.setVisibility(1);
                        mServerCountryIconUnitedKingdom.setVisibility(1);
                        mServerCountryIconUSA.setVisibility(1);*/
                    }
                    else if(mServerNameView.getText().toString().equals("193.182.144.78"))
                    {
                        mServerCountryName.setText("Israel");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.israel_icon);
                        mServerCountryIcon.setImageResource(R.drawable.israel_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("5.135.198.58"))
                    {
                        mServerCountryName.setText("Italy");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.italy_icon);
                        mServerCountryIcon.setImageResource(R.drawable.italy_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("45.32.50.218"))
                    {
                        mServerCountryName.setText("Japan");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.japan_icon);
                        mServerCountryIcon.setImageResource(R.drawable.japan_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("188.165.29.89"))
                    {
                        mServerCountryName.setText("Lithuania");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.lithuania_icon);
                        mServerCountryIcon.setImageResource(R.drawable.lithuania_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("176.31.35.150"))
                    {
                        mServerCountryName.setText("Netherlands");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.netherlands_icon);
                        mServerCountryIcon.setImageResource(R.drawable.netherlands_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("178.33.50.203"))
                    {
                        mServerCountryName.setText("Poland");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.poland_icon);
                        mServerCountryIcon.setImageResource(R.drawable.poland_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("94.23.76.58"))
                    {
                        mServerCountryName.setText("Portugal");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.portugal_icon);
                        mServerCountryIcon.setImageResource(R.drawable.portugal_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("91.195.98.71"))
                    {
                        mServerCountryName.setText("Romania");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.romania_icon);
                        mServerCountryIcon.setImageResource(R.drawable.romania_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("154.127.50.78"))
                    {
                        mServerCountryName.setText("South Africa");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.south_africa_icon);
                        mServerCountryIcon.setImageResource(R.drawable.south_africa_icon);
                    }
                    else  if(mServerNameView.getText().toString().equals("151.80.245.236"))
                    {
                        mServerCountryName.setText("Spain");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.spain_icon);
                        mServerCountryIcon.setImageResource(R.drawable.spain_icon);
                       // mServerCountryIconSpain.setVisibility(0);
                    }
                    else if(mServerNameView.getText().toString().equals("31.7.56.124"))
                    {
                        mServerCountryName.setText("Switzerland");
                        Uri imgUri=Uri.parse("android.resource://de.blinkt.openvpn.fragments/"+R.drawable.switzerland_icon);
                        mServerCountryIcon.setImageResource(R.drawable.switzerland_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("164.132.22.100"))
                    {
                        mServerCountryName.setText("United Kingdom");
                        Uri imgUri=Uri.parse("android.resource://"+R.drawable.united_kingdom_icon);
                        mServerCountryIcon.setImageResource(R.drawable.united_kingdom_icon);
                    }
                    else if(mServerNameView.getText().toString().equals("45.32.198.143"))
                    {
                        mServerCountryName.setText("USA");
                        Uri imgUri=Uri.parse("android.resource://"+R.drawable.usa_icon);
                        mServerCountryIcon.setImageResource(R.drawable.usa_icon);
                    }



                   // System.out.println(mServerNameView.getText());
                }

            });

            mPortNumberView.addTextChangedListener(new OnTextChangedWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (mConnection != null) {
                        mConnection.mServerPort = s.toString();
                    }
                }
            });


            mCustomOptionText.addTextChangedListener(new OnTextChangedWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (mConnection != null) {
                        mConnection.mCustomConfiguration = s.toString();
                    }
                }
            });

            mConnectSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser && mConnection != null) {
                        mConnectText.setText(String.valueOf(progress));
                        mConnection.mConnectTimeout = progress;
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            mConnectText.addTextChangedListener(new OnTextChangedWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (mConnection != null) {
                        try {
                            int t = Integer.valueOf(String.valueOf(s));
                            mConnectSlider.setProgress(t);
                            mConnection.mConnectTimeout = t;
                        } catch (Exception ignored) {
                        }
                    }
                }
            });

            mDeleteButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                            ab.setTitle(R.string.query_delete_remote);
                            ab.setPositiveButton(R.string.keep, null);
                            ab.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    removeRemote(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                }
                            });
                            ab.create().show();
                        }
                    }
            );


        }


    }


    @Override
    public ConnectionsAdapter.ConnectionsHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);

        View card;
        if (viewType == TYPE_NORMAL) {
            card = li.inflate(R.layout.server_card, viewGroup, false);

        } else { // TYPE_FOOTER
            card = li.inflate(R.layout.server_footer, viewGroup, false);
        }
        return new ConnectionsHolder(card, this, viewType);

    }

    static abstract class OnTextChangedWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    }

    @Override
    public void onBindViewHolder(final ConnectionsAdapter.ConnectionsHolder cH, int position) {
        if (position == mConnections.length) {
            // Footer
            return;
        }
        final Connection connection = mConnections[position];

        cH.mConnection = null;

        cH.mPortNumberView.setText(connection.mServerPort);
        cH.mServerNameView.setText(connection.mServerName);
       // cH.mServerNameView.setVisibility();
        cH.mPortNumberView.setText(connection.mServerPort);
        cH.mRemoteSwitch.setChecked(connection.mEnabled);


        cH.mConnectText.setText(String.valueOf(connection.getTimeout()));

        cH.mConnectSlider.setProgress(connection.getTimeout());


        cH.mProtoGroup.check(connection.mUseUdp ? R.id.udp_proto : R.id.tcp_proto);

        cH.mCustomOptionsLayout.setVisibility(connection.mUseCustomConfig ? View.VISIBLE : View.GONE);
        cH.mCustomOptionText.setText(connection.mCustomConfiguration);

        cH.mCustomOptionCB.setChecked(connection.mUseCustomConfig);
        cH.mConnection = connection;

    }


    private void removeRemote(int idx) {
        Connection[] mConnections2 = Arrays.copyOf(mConnections, mConnections.length - 1);
        for (int i = idx + 1; i < mConnections.length; i++) {
            mConnections2[i - 1] = mConnections[i];
        }
        mConnections = mConnections2;

    }

    @Override
    public int getItemCount() {
        return mConnections.length + 1; //for footer
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mConnections.length)
            return TYPE_FOOTER;
        else
            return TYPE_NORMAL;
    }

    void addRemote() {
        mConnections = Arrays.copyOf(mConnections, mConnections.length + 1);
        mConnections[mConnections.length - 1] = new Connection();
        notifyItemInserted(mConnections.length - 1);
        displayWarningIfNoneEnabled();
    }

    void displayWarningIfNoneEnabled() {
        int showWarning = View.VISIBLE;
        for (Connection conn : mConnections) {
            if (conn.mEnabled)
                showWarning = View.GONE;
        }
        mConnectionFragment.setWarningVisible(showWarning);
    }


    void saveProfile() {
        mProfile.mConnections = mConnections;
    }
}
