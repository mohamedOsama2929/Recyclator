package com.example.recyclator.recyclator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TipsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TipsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TipsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //My Variables
    @BindView(R.id.Info11)
    TextView mText11;
    @BindView(R.id.Info1)
    TextView mText1;
    @BindView(R.id.Info12)
    TextView mText12;
    @BindView(R.id.Info2)
    TextView mText2;
    @BindView(R.id.Info13)
    TextView mText13;
    @BindView(R.id.Info3)
    TextView mText3;
    @BindView(R.id.Info14)
    TextView mText14;
    @BindView(R.id.Info4)
    TextView mText4;
    @BindView(R.id.Info15)
    TextView mText15;
    @BindView(R.id.Info5)
    TextView mText5;
    @BindView(R.id.Info16)
    TextView mText16;
    @BindView(R.id.Info6)
    TextView mText6;
    @BindView(R.id.Info17)
    TextView mText17;
    @BindView(R.id.Info7)
    TextView mText7;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TipsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TipsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TipsFragment newInstance(String param1, String param2) {
        TipsFragment fragment = new TipsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        ButterKnife.bind(this,view);

        mText11.setText("تحقق من احتياج مركز إعادة التدوير الذي تتعامل معه إلى عملية فرز مسبقة للمخلفات.");

        mText1.setText("يُقصد بمصطلح \"التدوير أحادي الفرز\" أن يتم وضع كل المواد القابلة لإعادة التدوير في نفس السلة على أن تقع مسؤولية فرز الورق عن البلاستيك عن الزجاج وغير ذلك على مركز إعادة التدوير أو بلدية جمع القمامة. في المقابل، تشترط بعض المدن أو المراكز أن يتم فصل الأوراق عن البلاستيك عن الزجاج في حاويات منفصلة قبل تسليمها لهم. سوف يُفرض عليك فرز مخالفاتك قبل تسليمها إلى الجهة المعنية، وهي المهمة التي تحتاج لفترة من الوقت والمجهود، لذا استعد بشكل مسبق واسأل الجيران أو قم بالبحث عبر الإنترنت أو تواصل مع أحد الموظفين لمعرفة القوانين المنظمة للأمر.\n" +
                "\n" +
                "    إذا كانت مدينتك تتبع أسلوب \"التدوير أحادي الفرز\" فيمكنك أن تضع كل القمامة في سلة واحدة.\n" +
                "إذا كانت عملية الفرز المسبق مفروضة عليك فستحتاج إلى امتلاك حاويات منفصلة مخصصة لكل من الورق والبلاستيك والزجاج والمعادن.\n");

        mText12.setText("تعرف على أنواع الورق والورق المقوى القابلة لإعادة التدوير.");

        mText2.setText("يمكن لكل أنواع الورق تقريبًا – طالما أنها ليست متسخة بالأطعمة – أن يتم إعادة تدويرها. ينطبق ذلك على أوراق البريد والجرائد والمجلات والكتب القديمة وكراتين البيض وعبوات الحبوب وورق لف الهدايا. تأكد فقط من إزالة أي مواد أخرى ملتصقة بالورق، مثل: السلاسل أو المعادن (كتلك الموجودة في بطاقات الهدايا الفاخرة).");

        mText13.setText("تعرف على أنواع البلاستيك القابلة لإعادة التدوير.");

        mText3.setText("شعار إعادة التدوير يكون على شكل مثلث أخضر، وهي علامة مشهورة للغاية. إذا توفر هذا الشعار فوق البلاستيك فهي إشارة على أنه قابل لإعادة التدوير، وهو ما يعني أن كل من الزجاجات الفارغة (منزوعة الغطاء غير البلاستيكي) والحاويات والأكواب والحقائب وأواني الطعام البلاستيكية وكل ما شابه يمكن وضعه في سلة إعادة التدوير (الخاصة بالبلاستيك إذا كنت تستخدم عملية الفرز المنزلي للمخلفات). يمكن كذلك إعادة تدوير أي من الألعاب أو شماعات الملابس أو سلال القمامة وأطباق الطعام البلاستيكية، بشرط أن تكون نظيفة.\n" +
                "\n" +
                "    يمكن كذلك إعادة تدوير البوليستيرين \"ستايروفوم\". لاحظ فقط أنه يجب استخدام حقيبة بلاستيكية مغلقة في نقل قطع الفوم المستخدمة في الحماية أثناء تعبئة الأجسام الهشة (تُعرف باسم \"فوم الفول السوداني\" لتشابهها الكبير مع المكسرات الصغيرة وحبات الفول السوداني)، وذلك لتجنب الفوضى الهائلة الناتجة عن قطع الفوم بالغة الصغر تلك.\n" +
                "تشترط العديد من الجهات المعنية أن يتم تجميع وحزم الحقائب البلاستيكية حرة الحركة وذلك لتجنب طيرانها مع الهواء وسقوطها من الشاحنات متسببة في سقوطها على شكل قمامة في الشوارع.\n");

        mText14.setText("تعرف على أنواع المعادن الصالحة لإعادة التدوير.");

        mText4.setText(" قد تكون المعادن محيرة قليلًا للحكم على ما يصلح منها لإعادة التدوير وما لا يصلح، لكن كقاعدة عامة: يمكن اعتبار أن غالبية أنواع المعادن الشائعة في القمامة تكون قابلة بسهولة لإعادة التدوير، ونقصد هنا أشياءً مثل عبوات وعلب الألومنيوم والصلب أو الأواني الرقيقة المعدنية (الفويل) أو الأوعية والمقالي القديمة وعبوات الرش (أيروسول). في المقابل، احرص على التحقق من قبول جهة تدوير المخلفات للأشياء الأكبر حجمًا، مثل: ستائر الاستحمام أو قطع الأثاث المعدنية بالكامل.");

        mText15.setText("حافظ على العناصر سليمة لكي تتم عملية إعادة تدويرها بكفاءة.");

        mText5.setText("يحب الجميع متعة تقطيع الأوراق، لكن حقيقة الأمر أن الورقة الممزقة سوف تكون أقل فائدة مقارنة بالورقة السليمة عند إعادة تدويرها حيث يُهدر أغلبها إذا تمزقت. في حالة العبوات الألومنيوم، قد يتسبب تطبيقهم وسحقهم في فشل عملية إعادة تدويرهم نهائيًا. احرص دائمًا على إلقاء كل عنصر بحالته الطبيعية في سلة القمامة بمجرد الانتهاء من استخدامه، والمحافظة عليه كذلك أثناء عملية تخزين القمامة وتسلميها لمراكز إعادة التدوير.");

        mText16.setText("احتفظ بالعبوات والزجاجات القابلة للإرجاع أو إعادة الملء لربح بعض الأموال.");

        mText6.setText("يجب بالطبع ألا تفرط في زجاجات المياه الغازية أو أي عبوات مشابهة تُباع بالأساس على أنها قابلة لإعادة الملء والاستخدام، فهو ما يوفر عليك الكثير من الأموال عند إرجاعه والحصول على عبوة جديدة. يمكنك كذلك أن تحقق ربح مالي بسيط من خلال إرسال العبوات الألومنيوم والزجاجات الفارغة إلى مركز إعادة التدوير المحلي. بناءً على القوانين المنظمة، قد تجني القليل من الأموال نظير جمعك وإرسالك لهذه العبوات، وإذا لم يكن ذلك متاحًا، فاحرص على الأقل على جمعه وإرسالها من خلال خدمات إعادة التدوير العادية.");

        mText17.setText("تعرف جيدًا على المواد غير القابلة لإعادة التدوير.");

        mText7.setText("تأتي بقايا الطعام بالتأكيد في قائمة الأشياء غير الصالحة لإعادة التدوير. لا تقبل العديد من مراكز إعادة التدوير بالمناديل أو المناشف الورقية نهائيًا، خاصة إذا كانت متسخة أو ملوثة بالزيوت أو الشحوم. قد تكون العديد من مكونات القمامة قابلة لإعادة التدوير، لكن يوجد بعض العناصر المخادعة التي تبدو صالحة لإعادة التدوير لكنها ليست كذلك على الإطلاق. القائمة التالية هي أبرز المواد غير القابلة نهائيًا لإعادة التدوير:\n" +
                "\n" +
                "    زجاج البيركس.\n" +
                "    أكواب الشرب.\n" +
                "    عبوات ورق القصدير (مثل أكياس رقائق الطعام والحلوى وعبوات القهوة والشاي).\n" +
                "    الحفاضات.\n" +
                "    عبوات الكرتون المتسخة المستخدمة في حفظ الطعام (مثل: عبوات البيتزا). في المقابل يمكنك أن تقطع الأجزاء النظيفة.\n" +
                "    المصابيح الكهربائية.\n" +
                "    البطاريات والإلكترونيات.\n" +
                "    الطلاءات والدهانات.\n" +
                "المنظفات والزيوت والمذيبات… إلى آخره.");

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
