import 'dart:async';

mixin Validators {
  var emailValidator =
      StreamTransformer<String, String>.fromHandlers(handleData: (email, sink) {
    if (email.contains('@') && email.contains('.')) {
      sink.add(email);
      print('add: $email, to: $sink');
    } else {
      sink.add('Error');
      sink.addError("Email is not valid");
    }
  });

  var passwordValidator = StreamTransformer<String, String>.fromHandlers(
      handleData: (password, sink) {
    if (password.length > 4) {
      sink.add(password);
      //sink.addError(null);
      print('add: $password, to: $sink');
    } else {
      sink.add('Error');
      sink.addError("Password must be > 4");
    }
  });
}
