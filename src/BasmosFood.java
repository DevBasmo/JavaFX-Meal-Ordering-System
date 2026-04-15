
import javafx.collections.*;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.print.PrinterJob;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;


public class BasmosFood extends Application{
    
    private List<String> orderList = new ArrayList<>();
    private TextArea adminOrderArea = new TextArea();
    private final Map<String, Double> priceMap = new HashMap();
    private ToggleGroup orderTypeGroup;
    private RadioButton remoteRadio,onSiteRadio;
    private TextArea addressArea;
    private ComboBox<String> deliveryMenCombo,roleBox;
    private int loginAttempts = 0;
    private static final double STAGE_WIDTH = 700;
    private static final double STAGE_HEIGHT = 580;

    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage stage)  {
        initializePrices();
        stage.setTitle("Meal Ordering System");
        
       
        
    
     
        Scene scene = new Scene(createLoginTab(stage), STAGE_WIDTH, STAGE_HEIGHT);
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
        
        
        
    }
    
    
    
    //METHOD PRICE TAG
    private void initializePrices()
    {
    priceMap.put("Jollof Rice & Chicken per plate(#1500.00)", 1500.00);
    priceMap.put("Fried Rice & Chicken per plate (#1400.00)", 1400.00);
    priceMap.put("White Rice & Stew per plate (#1200.00)", 1200.00);
    priceMap.put("Goat Meat per Piece (#700.00)", 700.00);
    priceMap.put("Beaf per piece (#500.00)", 500.00);
    priceMap.put("Yam Poridge per plate (#1450.00)", 1450.00);
    priceMap.put("Pizza per Piece (#3500.00)", 3500.00);
    priceMap.put("Nkwobi per plate (#2000.00)", 2000.00);
    priceMap.put("Moi Moi per Piece (#500.00)", 500.00);
    priceMap.put("Egusi Soup & Pounded Yam per piece(#800.00)", 800.00);
    priceMap.put("Meat Pie per piece (#700.00)", 700.00);
    priceMap.put("Fruit Salad per plate (#1200.00)", 1200.00);
    priceMap.put("Donut per piece (#300.00)", 300.00);
    priceMap.put("Bottled Water per piece (#300.00)", 300.00);
    priceMap.put("Hollandia Yoghurt per Piece (#2000.00)", 2000.00);
    priceMap.put("Sharwarma per Piece (#3500.00)", 3500.00);
    priceMap.put("Amala & Ewedu per piece (#600.00)", 600.00);
    priceMap.put("Ponded Yam and gbegiri per piece (#700.00)", 700.00);
    priceMap.put("Burger & Fries per piece (#700.00)", 700.00);
    priceMap.put("Cockrah Fish per Piece (#700.00)", 700.00);
    priceMap.put("Tinu Eran per piece (#800.00)", 800.00); 
    
        
        
    }
    
    
    
    //Login Tab
   private BorderPane createLoginTab( Stage stage){
       BorderPane layout= new BorderPane();
        
        layout.setStyle("-fx-padding: 40; -fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 15;");
        
        
        ImageView logo = new ImageView (new Image("fastfood.jpeg"));
        logo.setFitHeight(75);
        logo.setFitWidth(105);
        logo.setPreserveRatio(true);
        
        Label heading = new Label("Basmos Fast Food");
        heading.setStyle(
                "-fx-font-size: 45px;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: #1e1e1e;"+
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 2, 0.5, 1, 1);"+
                "-fx-alignment: center;"        
        );
        
        BorderPane topBanner = new BorderPane();
        topBanner.setLeft(logo);
        topBanner.setCenter(heading);
        BorderPane.setAlignment(logo, Pos.CENTER_LEFT);
        BorderPane.setAlignment(heading, Pos.CENTER);
        topBanner.setPadding(new Insets(10));
        
        
        
        Label usernameLabel  = new Label ("Username:");
        usernameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 19px; -fx-text-fill: #003366");
        
        
       
      
        
        TextField usernameField = new TextField();
        usernameField.setFont(Font.font("Inter", 14));
        usernameField.setPromptText("Enter your user name ");
        usernameField.setMaxWidth(550);
        
        
        double passwordFieldHeight  = 36;
        Label passLabel =  new Label("Password:");
        passLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 19px; -fx-text-fill: #003366");
        PasswordField passField = new PasswordField();
        passField.setFont(Font.font("Inter", 14));
        passField.setPromptText("Enter your password");
        passField.setPrefHeight(passwordFieldHeight);
        passField.setMaxHeight(passwordFieldHeight);
        passField.setMinHeight(passwordFieldHeight);
 
        passField.setMaxWidth(550);
        
        
        
        
        TextField visiblePassField = new TextField();
        visiblePassField.setFont(Font.font("Inter, 14"));
        visiblePassField.setMaxWidth(550);
        visiblePassField.setManaged(false);
        visiblePassField.setVisible(false);
        visiblePassField.setPrefHeight(passwordFieldHeight);
        visiblePassField.setMaxHeight(passwordFieldHeight);
        visiblePassField.setMinHeight(passwordFieldHeight);
        visiblePassField.textProperty().bindBidirectional(passField.textProperty());
      
        
        
        CheckBox showPassword = new CheckBox("Show Password");
        showPassword.setStyle("-fx-font-size: 12px;");
        
        showPassword.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            visiblePassField.setVisible(isSelected);
            visiblePassField.setManaged(isSelected);
            passField.setVisible(!isSelected);
            passField.setManaged(!isSelected);
        
    
                });
        StackPane passwordBox = new StackPane(passField,visiblePassField);
        
        //role
        Label role = new Label("Role:");
        role.setStyle("-fx-font-weight: bold; -fx-font-size: 19px; -fx-text-fill: #003366");
        
        roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Admin", "Client");
        roleBox.setPromptText("Select Role");
        roleBox.setMaxWidth(550);
     
        roleBox.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px;");
       
        
        
        
        
        
        
        
      //Login Button
        
        Button loginButton = new Button("Login");
        loginButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #003366, #0055a5);"+
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;"  +
                "-fx-font-weight:bold;" +
                "-fx-padding:8 20 8 20;"+
                "-fx-background-radius: 10;"+
                "-fx-cursor: hand;"        
                
        );
       HBox button = new HBox (loginButton);
       button.setAlignment(Pos.CENTER);
        
        
        
        
        loginButton.setOnAction(e ->
        {
            Stage stagee = (Stage) loginButton.getScene().getWindow();
            String user = usernameField.getText();
            String pass = passField.getText();
            String roles = roleBox.getValue();
            
            if(user.equals("client") && pass.equals("client1234") && roles.equals("Client"))
            {

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Loggin successful! welcome "+user);
                alert.showAndWait();
                
                Parent clientUI = createOrderTab();
               // double width = stagee.getWidth();
                //double height = stagee.getHeight();
                
                Scene clientScene = new Scene(clientUI, STAGE_WIDTH, STAGE_HEIGHT);
                stage.setScene(clientScene);
                
}

  else if (user.equals("admin")&& pass.equals("admin1234") && roles.equals("Admin"))
            
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Admin login successful! Welcome " +user); 
                alert.showAndWait();

                //double width = stagee.getWidth();
                //double height = stagee.getHeight();
                
                Parent adminUI =  createAdminTab();
                
                Scene adminScene = new Scene(adminUI, STAGE_WIDTH, STAGE_HEIGHT);
                stage.setScene(adminScene);
}


                
                        
            
            else{
                loginAttempts++;
                
                if(loginAttempts >= 3)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Failed");
                    alert.setHeaderText("Too Many Attempts ");
                    alert.setContentText("You have entered incorrect credentials 3 times.\n The application will now close.");
                    alert.showAndWait();
                    
                    Platform.exit();
                    System.exit(0);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Login");
                    alert.setHeaderText("Attempt " + loginAttempts + " of 3");
                    alert.setContentText("Enter a valid username,password or role.");
                    alert.showAndWait();
                    
                    
                }
            }
        }
        );
        
        VBox loginField = new VBox(10);
        loginField.setAlignment(Pos.CENTER_LEFT);
        loginField.setPrefWidth(550);
        loginField.setMaxWidth(550);
        
        loginField.getChildren().addAll(usernameLabel, usernameField, passLabel, passwordBox, showPassword, role, roleBox, button );
        
       StackPane centerBox = new StackPane(loginField);
       centerBox.setAlignment(Pos.CENTER);
       
       layout.setTop(topBanner);
       layout.setCenter(centerBox);
       return layout;
       
       
   }
        
        
    
    //cLIENT CREATE ORDER TAB...
     public VBox createOrderTab()
    {
        VBox  layout = new VBox (10);
        layout.setStyle("-fx-padding: 40; -fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 15;");
       
        
         MenuBar menuBar = new MenuBar();
        
        Menu menu = new Menu("Options");
        
        
        //login page
        MenuItem loginPageItem = new MenuItem("Log Out");
        loginPageItem.setOnAction(e -> {
            Stage stage = (Stage)layout.getScene().getWindow();
            BorderPane loginRoot = createLoginTab(stage);
            Scene loginScene = new Scene (loginRoot, STAGE_WIDTH, STAGE_HEIGHT);
            stage.setScene(loginScene);
        });
        
        
        //Exit
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> Platform.exit());
        
        menu.getItems().addAll(loginPageItem, exitItem);
        menuBar.getMenus().add(menu);
        
        
         ImageView logo = new ImageView (new Image("fastfood.jpeg"));
        logo.setFitHeight(75);
        logo.setFitWidth(105);
        logo.setPreserveRatio(true);
        
        Label heading = new Label("Basmos Fast Food");
        heading.setStyle(
                "-fx-font-size: 45px;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: #1e1e1e;"+
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 2, 0.5, 1, 1);"+
                "-fx-alignment: center;"        
        );
        
        BorderPane topBanner = new BorderPane();
        topBanner.setLeft(logo);
        topBanner.setCenter(heading);
        BorderPane.setAlignment(logo, Pos.CENTER_LEFT);
        BorderPane.setAlignment(heading, Pos.CENTER);
        topBanner.setPadding(new Insets(10));
        
        
       
        
        Label title = new Label("Choose Your Meal:");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 19px; -fx-text-fill: #003366");
        ListView<String> mealList = new ListView<>();
        mealList.getItems().addAll(
                "Jollof Rice & Chicken per plate(#1500.00)",
                "Fried Rice & Chicken per plate (#1400.00)",
                "White Rice & Stew per plate (#1200.00)",
                "Goat Meat per Piece (#700.00)",
                "Beaf per piece (#500.00)",
                "Yam Poridge per plate (#1450.00)",
                "Pizza per Piece (#3500.00)",
                "Nkwobi per plate (#2000.00)",
                "Moi Moi per Piece (#500.00)",
                "Egusi Soup & Pounded Yam per piece(#800.00) ",
                "Meat Pie per piece (#700.00)",
                "Fruit Salad per plate (#1200.00)",
                "Donut per piece (#300.00)",
                "Bottled Water per piece (#300.00)",
                "Hollandia Yoghurt per Piece (#2000.00)",
                "Sharwarma per Piece (#3500.00)",
                "Amala & Ewedu per piece (#600.00)",
                "Ponded Yam and gbegiri per piece (#700.00)",
                "Burger & Fries per piece (#700.00)",
                "Cockrah Fish per Piece (#700.00)",
                "Tinu Eran per piece (#800.00)"
                
        );
        mealList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
    
        
        Map < String, SimpleBooleanProperty> selectedMap = new HashMap<>();
        for(String item: mealList.getItems()){
            selectedMap.put(item, new SimpleBooleanProperty(false));
        }
        
        mealList.setCellFactory(CheckBoxListCell.forListView(item->
        {
            SimpleBooleanProperty prop = selectedMap.get(item);
            prop.addListener((obs, oldVal, newVal) ->{
            if(newVal){
                if(!orderList.contains(item))orderList.add(item);
                
            }
            else{
                orderList.remove(item);
            }
        });
        return prop;
        }));

        
        mealList.setMaxHeight(360);
        
        Label label = new Label("Order Type: ");
        
         onSiteRadio = new RadioButton("On-site");
         remoteRadio = new RadioButton("Remote");
        
        orderTypeGroup = new ToggleGroup();
        onSiteRadio.setToggleGroup(orderTypeGroup);
        remoteRadio.setToggleGroup(orderTypeGroup);
        
        //onSiteRadio.setSelected(true);
        
        onSiteRadio.setFont(Font.font("Arial", 14));
        onSiteRadio.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-text-fill: #555555");
        remoteRadio.setFont(Font.font("Arial", 14));
        remoteRadio.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-text-fill: #555555");
        
        label.setFont(Font.font("Arial", 16));
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 19px; -fx-text-fill: #333333");
        
        
        HBox radioRow  = new HBox(20);
        
        radioRow.getChildren().addAll(onSiteRadio, remoteRadio);
        radioRow.setAlignment(Pos.CENTER_RIGHT);
        HBox radioBox = new HBox(5);
        radioBox.setPadding(new Insets(10));
        
        
        radioBox.getChildren().addAll(label, radioRow);
        
        Label addressLabel = new Label("Enter Address:");
        addressLabel.setFont(Font.font("Arial", 16));
        addressLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 19px; -fx-text-fill: #333333");
        
        addressArea = new TextArea();
        addressArea.setPrefWidth(300);
        addressArea.setPrefHeight(100);
        
        HBox addressBox = new HBox(15, addressLabel, addressArea);
        HBox.setHgrow(addressArea, Priority.ALWAYS);
        
        VBox area = new VBox(15, radioBox, addressBox);
        Button orderButton =new Button("Place Order");
        HBox orderBtn =new HBox(10,orderButton);
       orderBtn.setAlignment(Pos.CENTER);
        orderTypeGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle)->{
             if (newToggle != null && newToggle instanceof RadioButton){
                 RadioButton selected = (RadioButton) newToggle;
                        
                        if ( selected == remoteRadio)
                        {
                            
                            
                            addressArea.setDisable(false);
                            
                        }else{
                            
                            
                            addressArea.setDisable(true);
                            
                        }
             } });
       
        
         
        
        orderButton.setOnAction(e -> {
            List<String> selected = mealList.getSelectionModel().getSelectedItems();
            if(selected.size() == 0)
            {
               Alert alert = new Alert(Alert.AlertType.WARNING, "Please select atleast one meal to proceed.");
                alert.showAndWait();
            }
            else
            {
                orderList.addAll(selected);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order placed! Admin will confirm shortly.");
                alert.showAndWait();
                mealList.getSelectionModel().clearSelection();
                showReceipt();
                                
            }
            

            
        });
        styleButton(orderButton);
        layout.getChildren().addAll(menuBar,topBanner,title, mealList,radioBox,area,orderBtn);
      
        return layout;
    }
    
   
     
     
     
     
     //ADMIN METRHOD STAGE
    private VBox createAdminTab()
    {
        VBox layout = new VBox (20);
        layout.setStyle("-fx-padding: 40; -fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 15;");
        
        
        
       
        //Menus Bar
        MenuBar menuBar = new MenuBar();
        
        Menu menu = new Menu("Options");
        
        
        //login page
        MenuItem loginPageItem = new MenuItem("Log Out");
        loginPageItem.setOnAction(e -> {
            Stage stage = (Stage)layout.getScene().getWindow();
            BorderPane loginRoot = createLoginTab(stage);
            Scene loginScene = new Scene (loginRoot, STAGE_WIDTH, STAGE_HEIGHT);
            stage.setScene(loginScene);
        });
        
        
        //Exit
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> Platform.exit());
        
        menu.getItems().addAll(loginPageItem, exitItem);
        menuBar.getMenus().add(menu);
        
        
        
         ImageView logo = new ImageView (new Image("fastfood.jpeg"));
        logo.setFitHeight(75);
        logo.setFitWidth(105);
        logo.setPreserveRatio(true);
        
        Label heading = new Label("Basmos Fast Food");
        heading.setStyle(
                "-fx-font-size: 45px;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: #1e1e1e;"+
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 2, 0.5, 1, 1);"+
                "-fx-alignment: center;"        
        );
        
        BorderPane topBanner = new BorderPane();
        topBanner.setLeft(logo);
        topBanner.setCenter(heading);
        BorderPane.setAlignment(logo, Pos.CENTER_LEFT);
        BorderPane.setAlignment(heading, Pos.CENTER);
        topBanner.setPadding(new Insets(10));
        
        
        Label label = new Label("Order List:");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 19px; -fx-text-fill: #003366");
        
        adminOrderArea .setEditable(false);
        adminOrderArea.setPrefHeight(450);
        Button refreshButton = new Button("Refresh Orders");
        Button confirmButton = new Button("Confirm Orders");
        
        HBox butt = new HBox(20,refreshButton, confirmButton);
        butt.setAlignment(Pos.CENTER);
        
        Label deliveryLabel = new Label("Assign Delivery Man:");
        deliveryLabel.setFont(Font.font("Arial", 16));
        deliveryLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 19px; -fx-text-fill: #333333");
        
                
        
        deliveryMenCombo = new ComboBox<>();
        deliveryMenCombo.getItems().addAll("James", "Emeka", "Sani", "Kelechi", "Bayo");
        deliveryMenCombo.setPromptText("Select Delivery Man");
        deliveryMenCombo.setPrefWidth(250);
        deliveryMenCombo.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px;");
        
              
        
        HBox deliveryBox = new HBox(20, deliveryLabel, deliveryMenCombo);
        orderTypeGroup = new ToggleGroup();
        orderTypeGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle)->{
             if (newToggle != null && newToggle instanceof RadioButton){
                 RadioButton selected = (RadioButton) newToggle;
                        
                        if (selected != null && selected.equals(remoteRadio))
                        {
                            
                            
                            deliveryMenCombo.setDisable(false);
                            
                        }else{
                            
                            
                            deliveryMenCombo.setDisable(true);
                            deliveryMenCombo.getSelectionModel().clearSelection();
                        }
             } });
       
        
        
        confirmButton.setOnAction(e ->
        {
           Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order Sent!");
            alert.showAndWait();
            orderList.clear();
            adminOrderArea.clear();
            deliveryMenCombo.setValue(null);
              deliveryMenCombo.getEditor().clear();
                    deliveryMenCombo.setButtonCell(new ListCell<>() {
               
               protected void updateItem(String item, boolean empty)
               {
                   super.updateItem(item, empty);
                   setText(empty || item == null ? deliveryMenCombo.getPromptText() : item);
               }
           });
                  
              
        }
        );
        
        refreshButton.setOnAction(e ->
        {
            String addresss = addressArea.getText();
                if (orderList.isEmpty()) 
                {
                    adminOrderArea.setText("No new orders.");
                     deliveryMenCombo.setValue(null);
                    deliveryMenCombo.getEditor().clear();
                    deliveryMenCombo.setButtonCell(new ListCell<>() {
               
               protected void updateItem(String item, boolean empty)
               {
                   super.updateItem(item, empty);
                   setText(empty || item == null ? deliveryMenCombo.getPromptText() : item);
               }
           });
                    
                }
                
                else
                {
                    
                   
                    double total = 0.0;
                    StringBuilder sb = new StringBuilder("Confirm Orders:\n");
                    for(String order : orderList){
                        sb.append("- ").append(order).append("\n");
                        total += priceMap.getOrDefault(order, 0.0);
                    }
                    sb.append("\nTotal Price: #").append(total);
                    
                   
                     if (!addresss.isEmpty())
                   {
                       sb.append("\nDelivery Address : "+addresss);
                   }else{
                       sb.append("\nOn site!");
                   }
                     
                   adminOrderArea.setText(sb.toString());  
                  
                   
                  // orderList.clear();
                   
                }
                 
                
        } );
        styleButton(confirmButton);
        styleButton(refreshButton);
        layout.getChildren().addAll(menuBar,topBanner,label, adminOrderArea,deliveryBox, butt);
        return layout;
    }
    
    
    
    
    
    //BUTTON STYLE...
    private void styleButton(Button button)
    {
        String baseStyle = "-fx-background-color: linear-gradient(to right, #003366, #0055a5);"
                + "-fx-text-fill: white;"
                + "-fx-font-size: 15px;"
                + "-fx-font-weight: bold;"
                + "-fx-padding: 10 24 10 24;"
                + "-fx-background-radius: 10"
                + "-fx-cursor: hand;"
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 4, 0, 0, 2);";
        
        button.setStyle(baseStyle);
        
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #0055a5[;  -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold; -fx-padding: 10 24 10 24; -fx-background-radius: 10; -fx-cursor: hand;"
        ));
        button.setOnMouseExited(e -> button.setStyle(baseStyle));
    }
    
   
    
    
    
    //RECEIPT METHOD......
   private void showReceipt()
        {
            String addresss = addressArea.getText();
            StringBuilder ticket = new StringBuilder();
            
            ticket.append("\n=================== BASMOS FOOD RECEIPT ==================\n\n");
            
            double total = 0.0;
            
            for (String item : orderList)
            {
                Double price = priceMap.get(item);
                if(price != null)
                {
                    ticket.append(item).append(" - #").append(String.format("%.2f", price)).append("\n");
                    total += price;
                }
                
                else
                {
                    ticket.append(item).append("-#Unknown\n");
                    
                }
               
                
            }
            
                    
                   ticket.append("\n-----------------------------------------------------------\n\n");
                   ticket.append("Total: #").append(String.format("%.2f", total)).append("\n");
                   
                   
                   if (!addresss.isEmpty())
                   {
                       ticket.append("\nDelivery Address : "+addresss);
                   }else{
                       ticket.append("\nOn site!");
                   }
                   
                   ticket.append("\n===========================================================\n");
                   ticket.append("Thank you for choosing Basmos Fast Food!");
            
            TextArea ticketArea = new TextArea(ticket.toString());
            ticketArea.setEditable(false);
            ticketArea.setPrefSize(250, 450);
            ticketArea.setStyle("-fx-font-family: monospace; -fx-font-size: 13px;");
            
            
            Button printButton = new Button("Print Receipt");
            printButton.setOnAction( e->{
                
               
               // Alert alert = new Alert(Alert.AlertType.INFORMATION);
              //  alert.setTitle("INFO");
               // alert.setHeaderText(null);
               // alert.setContentText("Printing Receipt...");
               // alert.showAndWait();
               
               PrinterJob printerJob = PrinterJob.createPrinterJob();        
               if (printerJob != null) {          
                   // Show the printer selection dialog      
                   boolean printerSelected = printerJob.showPrintDialog(null);
                   // null uses the default stage  
                   if (printerSelected) {         
                       // Create a Text node for printing (TextArea isn't ideal for printing)     
                       Text printText = new Text(ticketArea.getText());          
                       printText.setFont(javafx.scene.text.Font.font("Monospaced", 12));     
                       // Print the content             
                       boolean printSuccess = printerJob.printPage(printText);     
                       if (printSuccess) {    
                           printerJob.endJob();      
                           Alert alert = new Alert(Alert.AlertType.INFORMATION);  
                           alert.setTitle("Print Success");         
                           alert.setHeaderText(null);           
                           alert.setContentText("Receipt printed successfully!");   
                           alert.showAndWait();      
                       } 
                       else {           
                           Alert alert = new Alert(Alert.AlertType.ERROR);    
                           alert.setTitle("Print Failed");            
                           alert.setHeaderText(null);           
                           alert.setContentText("Failed to print the receipt.");   
                           alert.showAndWait();            
                       }             } 
                   else {               
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);    
                       alert.setTitle("Print Cancelled");            
                       alert.setHeaderText(null);      
                       alert.setContentText("Printing was cancelled.");   
                       alert.showAndWait();     
                   } 
               }
               else {           
                   Alert alert = new Alert(Alert.AlertType.ERROR);   
                   alert.setTitle("Printer Error");         
                   alert.setHeaderText(null);        
                   alert.setContentText("No printers available.");    
                   alert.showAndWait();     
               }   
            });
                
            
//);
            
            VBox layout = new VBox(10, ticketArea, printButton);
            layout.setPadding(new Insets(20));
            layout.setAlignment(Pos.CENTER);
            
            Scene scene = new Scene(layout, 450, 450);
            Stage ticketStage  = new Stage();
            ticketStage.setWidth(530);
            ticketStage.setHeight(350);
            ticketStage.setTitle("Basmos Fast Food Receipt");
            ticketStage.setScene(scene);
            ticketStage.show();
           
            
        }
                   
    
}