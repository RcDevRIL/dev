
import 'package:login_learn_bloc3/widgets/log_out_button.dart';
import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  ///
  /// Prevents the use of the "back" button
  ///
  Future<bool> _onWillPopScope() async {
    return false;
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: _onWillPopScope,
      child: SafeArea(
        child: Scaffold(
          appBar: AppBar(
            title: Text('Home Page'),
            leading: Container(),
            actions: <Widget>[
              LogOutButton(),
            ],
          ),
          body: Container(
            child: Column(
              children: <Widget>[],
            ),
          ),
        ),
      ),
    );
  }
}
