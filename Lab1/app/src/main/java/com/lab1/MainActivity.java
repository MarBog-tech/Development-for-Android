package com.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @SuppressLint("NonConstantResourceId")
    public void productSelection(View view) {
        TextView textView = findViewById(R.id.text_selected);
        RadioGroup groupFirm = findViewById(R.id.radioGroup1);
        int checkedGroupIndex = groupFirm.getCheckedRadioButtonId();

        RadioGroup groupProduct = findViewById(R.id.radioGroup2);
        int checkedProductIndex = groupProduct.getCheckedRadioButtonId();

        switch (checkedGroupIndex){
            case R.id.samsung:
                switch (checkedProductIndex){
                    case R.id.refrigerator:
                        textView.setText("657L SpaceMax™ Family Hub Side by side Refrigerator RS74T5F01B4\n\n" +
                                "Samsung 336 L 3 Star Inverter Frost Free Double Door Refrigerator (RT37A4633S8/HL, Silver, Elegant Inox, Curd Maestro)");
                        break;
                    case R.id.washing_machine:
                        textView.setText("Front Loader Washing Machine 8kg Inox/White\n\n" +
                                "Samsung 6.5 kg Fully-Automatic Top Loading Washing Machine (WA65A4002VS/TL, Imperial Silver, Diamond Drum)");
                        break;
                    case R.id.laptop:
                        textView.setText("Ноутбук Samsung Galaxy Book Odyssey Laptop (NP762XDA-XA1US)\n\n" +
                                "amsung Galaxy Book Flex Intel Core i7 Processor 13.3 inches 1920 x 1080, QLED Business Laptop with QLED Display and Bluetooth-Enabled S Pen (512GB, Windows 10 Home), 1.16 kg");
                        break;
                    case R.id.microwave:
                        textView.setText("MG23K3515AK Grill MWO with Browning Plus, 23L\n\n" +
                                "Solo Microwave Oven with Healthy Steam, 28L");
                        break;
                    default:
                        textView.setText("Product type si not selected");
                };
                break;
            case R.id.philips:
                switch (checkedProductIndex){
                    case R.id.refrigerator:
                        textView.setText("Philips Fridge Only 310L 73-317C");
                        break;
                    case R.id.washing_machine:
                        textView.setText("THBPFLWM01 PHILIPS W085 WASHING MACHINE CREAM");
                        break;
                    case R.id.laptop:
                        textView.setText("Philips 15NB5800 laptop\n\n" +
                                "Philips Laptop Soundbar SPA-1100 (Genuine)");
                        break;
                    case R.id.microwave:
                        textView.setText("Philips Mild Steel Microwave Oven, For Personal");
                        break;
                    default:
                        textView.setText("Product type si not selected");
                };
                break;
            case R.id.bosch:
                switch (checkedProductIndex){
                    case R.id.refrigerator:
                        textView.setText("Bosch 800 Series 25 Cu. Ft. Standard Depth French Door Refrigerator-Stainless Steel\n\n" +
                                "B36CD50SNS Bosch 500 Series 36\" French Door Refrigerator - Stainless Steel");
                        break;
                    case R.id.washing_machine:
                        textView.setText("Bosch 7 kg 5 Star Inverter Touch Control Fully Automatic Front Loading Washing Machine with In - built Heater (WAJ2416SIN, Silver)");
                        break;
                    case R.id.laptop:
                        textView.setText("Bosch DCU220 Rugged Laptop\n\n" +
                                "GETAC V110 G3 (Bosch DCU 220) i5 8 tactile Go 256 Go W10P Fully Rugged Notebook");
                        break;
                    case R.id.microwave:
                        textView.setText("Bosch BEL554MS0 Microwave OvenBosch BEL554MS0 Microwave OvenBosch BEL554MS0 Microwave OvenMore picturesplay\n\n" +
                                "Bosch BEL554MS0 Serie 6 Microwave oven with grill cm. 60 h. 38 - black / stainless steel");
                        break;
                    default:
                        textView.setText("Product type si not selected");
                };
                break;
            default:
                textView.setText("Not selected firm");
        }
    }


    public void cancelChoice(View view) {
        TextView textView = findViewById(R.id.text_selected);
        textView.setText("");

        RadioGroup groupFirm = findViewById(R.id.radioGroup1);
        groupFirm.clearCheck();

        RadioGroup groupProduct = findViewById(R.id.radioGroup2);
        groupProduct.clearCheck();

    }
}