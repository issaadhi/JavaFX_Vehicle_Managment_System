package controller.appHome;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Payment;
import model.User;
import model.VMService;
import model.Vehicle;
import services.MaintainService;
import services.PaymentService;
import services.UserService;
import services.VehicleService;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class HomeDetailPaneController implements Initializable {

    @FXML
    private AnchorPane detailAnchorPane;

    @FXML
    ImageView imageView;


    @FXML
    private Label label;

    @FXML
    private Label label2;

    @FXML
    private Label userlabel;

    @FXML
    private Label vehlabel;

    @FXML
    private Label serlabel;

    @FXML
    private Label paylabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        slideShow();
        time();
        nousers();
        novehicles();
        noservice();
        nopayment();


    }

    int count;

    public void slideShow(){
        ArrayList <Image> images = new ArrayList<Image>();
        images.add(new Image("/images/f2628c4827cb50ad315be4372218313b.png"));
        images.add(new Image("/images/5.png"));
        images.add(new Image("/images/3.gif"));
        images.add(new Image("/images/Avrios-ebook-Cost-management-832x540@100.png"));


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
            imageView.setImage(images.get(count));
            count++;
            if (count == 4)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        }

    public void time(){
        Thread clock = new Thread(() -> {
            try {
                for(;;) {
                    Calendar cal = new GregorianCalendar();
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);

                    label.setText("Today is " + day + "." + month + "." + year);
                    String timer = " " + hour + ":" + minute + ":" + second + " ";
                    Platform.runLater(()->label2.setText(timer));

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        clock.start();
    }

    public void nousers() {
        UserService user = new UserService();
        ObservableList<User> userObservableList = user.loadAllUser();

        int noofuser = userObservableList.size();

        userlabel.setText("User count : " + noofuser);

    }


    public void novehicles () {
        VehicleService vehicle = new VehicleService();
        ObservableList<Vehicle>  vehicleObservableList = VehicleService.getVehicleList();

        int noofveh = vehicleObservableList.size();

        vehlabel.setText("Vehicle count : " + noofveh);

    }

    public void noservice () {
        MaintainService maintainService = new MaintainService();
        ObservableList<VMService> serviceObservableList = MaintainService.getMaintenanceList();

        int noofser = serviceObservableList.size();

        serlabel.setText("Services completed :" + noofser);
    }

    public void nopayment(){
        PaymentService paymentService = new PaymentService();
        ObservableList<Payment> paymentObservableList = PaymentService.getPaymentList();

        int noofpay = paymentObservableList.size();

        paylabel.setText("Sales done : " + noofpay);
    }

    }



