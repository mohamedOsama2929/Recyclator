package com.example.recyclator.recyclator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recyclator.recyclator.cotrollers.app_controllers.Utilities;
import com.example.recyclator.recyclator.cotrollers.network_controllers.Service;
import com.example.recyclator.recyclator.models.Material;
import com.example.recyclator.recyclator.models.SendRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class uploadTrashActivity extends AppCompatActivity {
    private static final String READ_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final String WRITE_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String CAMERA = Manifest.permission.CAMERA;
    private static final int External_Permission_Request_code = 0505;
    final int PICK_IMAGE = 182;
    @BindView(R.id.back)

    ImageView view;
    Spinner spinnerMaterial;
    String selectedImagePath;
    int userId = 3;
    EditText quantity;
    int materialID = 0;
    String TAG = "uploadTrashActivity";
    List<Material> materials;
    private boolean mExternalPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_trash);
        ButterKnife.bind(this);

        spinnerMaterial = (Spinner) findViewById(R.id.spinner_material);
        quantity = (EditText) findViewById(R.id.quantity);

        Service.Fetcher.getInstance().getAllMaterial().enqueue(new Callback<List<Material>>() {
            @Override
            public void onResponse(Call<List<Material>> call, Response<List<Material>> response) {

                materials = response.body();
                ArrayList<String> values = new ArrayList<>();
                for (int i = 0; i < materials.size(); i++) {

                    values.add(materials.get(i).getName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(uploadTrashActivity.this, android.R.layout.simple_dropdown_item_1line, values);
                spinnerMaterial.setAdapter(adapter);
                Log.e(TAG, response.body().toString() + "");
            }

            @Override
            public void onFailure(Call<List<Material>> call, Throwable t) {

                Log.e(TAG, t.getMessage().toString() + "");
            }
        });

        spinnerMaterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                materialID = materials.get(position).getID();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(uploadTrashActivity.this, MainActivity.class));
            }
        });
    }

    private void getExternalPermission() {
        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                READ_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            mExternalPermissionGranted = true;

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    WRITE_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                mExternalPermissionGranted = true;

                if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                        CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    mExternalPermissionGranted = true;

                } else {
                    ActivityCompat.requestPermissions(this, permission, External_Permission_Request_code);
                }
            } else {
                ActivityCompat.requestPermissions(this, permission, External_Permission_Request_code);
            }
        } else {
            ActivityCompat.requestPermissions(this, permission, External_Permission_Request_code);
        }

    }

    public void uploadImage(View view) {
        getExternalPermission();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE);
        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null && resultCode != RESULT_CANCELED) {

            Uri uri = data.getData();

            if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
                selectedImagePath = Utilities.getRealPathFromURI_API11to18(uploadTrashActivity.this, uri);
            } else if (Build.VERSION.SDK_INT > 19) {

                selectedImagePath = Utilities.getRealPathFromURI_API19(uploadTrashActivity.this, uri);
            } else {
                selectedImagePath = Utilities.getRealPathFromURI_BelowAPI11(uploadTrashActivity.this, uri);
            }

        }

    }

    public void sendRequest(View view) {


        if (selectedImagePath == "" || selectedImagePath == null) {
            Toast.makeText(uploadTrashActivity.this, "Image must not be empty", Toast.LENGTH_SHORT).show();
        } else {

//        File file = new File(selectedImagePath);
//        RequestBody imageFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("Image", file.getName(), imageFile);
//        RequestBody userID = RequestBody.create(MediaType.parse("text/plain"), userId+"");
//
//        RequestBody materialId = RequestBody.create(MediaType.parse("text/plain"), materialID+"");
//        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "Name");
//        RequestBody Quantity = RequestBody.create(MediaType.parse("text/plain"), quantity.getText().toString());
//        RequestBody price = RequestBody.create(MediaType.parse("text/plain"), "30");


            Service.Fetcher.getInstance().sendRequest("Image", userId, materialID, "Name", Integer.parseInt(quantity.getText().toString()), 4).enqueue(new Callback<SendRequest>() {
                @Override
                public void onResponse(Call<SendRequest> call, Response<SendRequest> response) {


                    Toast.makeText(uploadTrashActivity.this, response.body().getStatus() + "", Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onFailure(Call<SendRequest> call, Throwable t) {

                    Log.e(TAG, t.getMessage() + "");
                }
            });
        }
    }
}



