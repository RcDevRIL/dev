import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:login_learn_bloc_2/home-page.dart';
import 'bloc.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'bloc login',
      theme: ThemeData(
        primarySwatch: Colors.teal,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({
    Key key,
  }) : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  _logon(BuildContext context) {
    Navigator.of(context).push(MaterialPageRoute(
      builder: (context) => RealHomePage(),
    ));
  }

  @override
  Widget build(BuildContext context) {
    final bloc = LoginBloc();
    return Scaffold(
      appBar: AppBar(
        title: Text('bloc login'),
      ),
      body: SingleChildScrollView(
        child: Container(
          height: MediaQuery.of(context).size.height,
          padding: EdgeInsets.all(16.0),
          child: Column(
            mainAxisSize: MainAxisSize.max,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              StreamBuilder<String>(
                  stream: bloc.emailStream,
                  builder: (context, snapshot) {
                    return TextField(
                      onChanged: (s) => bloc.emailChanged.add(s),
                      keyboardType: TextInputType.emailAddress,
                      decoration: InputDecoration(
                        border: OutlineInputBorder(),
                        hintText: "Enter email",
                        labelText: "Email",
                        errorText: snapshot.error,
                      ),
                    );
                  }),
              SizedBox(
                height: 20.0,
              ),
              StreamBuilder<String>(
                  stream: bloc.passwordStream,
                  builder: (context, snapshot) {
                    return TextField(
                      onChanged: (s) => bloc.passwordChanged.add(s),
                      keyboardType: TextInputType.text,
                      obscureText: true,
                      decoration: InputDecoration(
                        border: OutlineInputBorder(),
                        hintText: "Enter password",
                        labelText: "Password",
                        errorText: snapshot.error,
                      ),
                    );
                  }),
              SizedBox(
                height: 20.0,
              ),
              StreamBuilder<bool>(
                  stream: bloc.submitCheck,
                  builder: (context, snapshot) {
                    return RaisedButton(
                      color: Colors.blueAccent,
                      onPressed:
                          snapshot.hasError ? () => _logon(context) : null,
                      child: Text(snapshot.hasData.toString()),
                    );
                  }),
            ],
          ),
        ),
      ),
    );
  }
}
