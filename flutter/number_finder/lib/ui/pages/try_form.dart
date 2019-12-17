import 'package:flutter/material.dart';
import 'package:logger/logger.dart';
import 'package:number_finder/data/models/user_repo.dart';
import 'package:number_finder/data/services/game_api.dart';
import 'package:number_finder/ui/pages/pages.dart';
import 'package:provider/provider.dart';

class TryForm extends StatefulWidget {
  static const String routeName = '/try';
  final log = Logger();

  TryForm({Key key}) : super(key: key);

  @override
  _TryFormState createState() => _TryFormState();
}

class _TryFormState extends State<TryForm> {
  final _formKey = GlobalKey<FormState>();
  TextEditingController _tryController;
  SnackBar tryResult;

  Future<bool> _onWillPopScope() async {
    return false;
  }

  @override
  void initState() {
    super.initState();
    _tryController = TextEditingController();
  }

  @override
  void dispose() {
    _tryController?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: _onWillPopScope,
      child: SafeArea(
        child: Scaffold(
          appBar: AppBar(
            leading: Container(),
            title: const Text('Try a number!'),
            actions: <Widget>[
              IconButton(
                onPressed: () {
                  _redirectToPage(context, HomePage());
                },
                icon: Icon(Icons.exit_to_app),
              ),
            ],
          ),
          body: InkWell(
            onTap: () => FocusScope.of(context).unfocus(),
            child: Form(
              key: _formKey,
              child: Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    Container(
                      width: 250.0,
                      child: TextFormField(
                        controller: _tryController,
                        keyboardType:
                            TextInputType.numberWithOptions(signed: false),
                        keyboardAppearance: Brightness.dark,
                        decoration: const InputDecoration(
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.all(
                              Radius.circular(8.0),
                            ),
                            borderSide: BorderSide(
                              color: Colors.blueGrey,
                              style: BorderStyle.solid,
                              width: 2.0,
                            ),
                          ),
                          hintText: 'Enter a number',
                          labelText: 'Number',
                        ),
                        validator: (value) {
                          if (value.isEmpty) {
                            return 'Please enter some number';
                          }
                          return null;
                        },
                      ),
                    ),
                    SizedBox(
                      height: 12.0,
                    ),
                    Padding(
                      padding: const EdgeInsets.symmetric(vertical: 16.0),
                      child: RaisedButton(
                        onPressed: () async {
                          // Validate will return true if the form is valid, or false if
                          // the form is invalid.
                          FocusScope.of(context).unfocus();
                          if (_formKey.currentState.validate()) {
                            var response = await Provider.of<GameAPI>(context)
                                .checkValue(
                                    Provider.of<UserRepo>(context).getToken,
                                    _tryController.text);
                            if (response.statusCode == 200) {
                              debugPrint(response.body);
                              if (response.body.contains('C\'est bon !!!')) {
                                _buildSnackBar(true, response.body);
                                Scaffold.of(context).showSnackBar(tryResult);
                              } else {
                                _buildSnackBar(false, response.body);
                                Scaffold.of(context).showSnackBar(tryResult);
                              }
                            } else {
                              showDialog(
                                context: context,
                                builder: (context) => AlertDialog(
                                  title: Text(
                                    'Erreur',
                                    style: TextStyle(
                                      fontWeight: FontWeight.w700,
                                    ),
                                  ),
                                  content: Text('Erreur avec l\'API...\n' +
                                      'Response code: ${response.statusCode}\n' +
                                      'Response body: ${response.body}'),
                                  actions: <Widget>[
                                    RaisedButton(
                                      onPressed: () =>
                                          Navigator.of(context).pop(),
                                      color: Colors.blue,
                                      child: Text(
                                        'OK',
                                        style: TextStyle(
                                          color: Colors.white,
                                        ),
                                      ),
                                    )
                                  ],
                                ),
                              );
                            }
                          }
                        },
                        child: Text('Submit'),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }

  SnackBar _buildSnackBar(bool success, String body) {
    return SnackBar(
      backgroundColor: success ? Colors.green : Colors.red,
      content: Text(body),
    );
  }

  void _redirectToPage(BuildContext context, Widget page) {
    WidgetsBinding.instance.addPostFrameCallback((_) {
      MaterialPageRoute newRoute =
          MaterialPageRoute(builder: (BuildContext context) => page);
      Navigator.of(context)
          .pushAndRemoveUntil(newRoute, ModalRoute.withName('/decision'));
    });
  }
}
