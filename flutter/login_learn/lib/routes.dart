import 'package:flutter/material.dart';
import 'screens/login_screen.dart';
import 'screens/RealHomePage.dart';
import 'screens/patient_screens/PatientResumePage.dart';

class Routes{
  Routes() {
    runApp(new MaterialApp(
      title: 'ErgoLife_proto',
      debugShowCheckedModeBanner: false,
      home: new LoginScreen(),
      onGenerateRoute: (RouteSettings settings){
        switch(settings.name){
          case '/login':
            return new MyCustomRoute(
              builder: (_) => new LoginScreen(),
              settings: settings,
            );

          case '/home':
            return new MyCustomRoute(
              builder: (_) => new RealHomePage(),
              settings: settings,
            );
          case '/patientResume':
            return new MyCustomRoute(
              builder: (_) => new PatientResumePage(),
              settings: settings,
            );
        }
      },
    ));
  }
}

class MyCustomRoute<T> extends MaterialPageRoute<T>{
  MyCustomRoute({WidgetBuilder builder, RouteSettings settings})
    : super(builder: builder, settings: settings);

  @override
  Widget buildTransitions(BuildContext context, Animation<double> animation,
      Animation<double> secondaryAnimation, Widget child) {
    if(settings.isInitialRoute) return child;
    return new FadeTransition(opacity: animation, child: child);
  }
}