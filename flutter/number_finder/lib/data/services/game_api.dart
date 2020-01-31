import 'dart:collection';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import 'package:logger/logger.dart';
import 'dart:convert';

class GameAPI with ChangeNotifier {
  final log = Logger();

  final String baseUrl = 'http://numberfinder.neodigit.fr';

  Future<Response> startGame(String nickName) async {
    var jsonResponse;
    log.d('Calling "/startGame" with parameters: {$nickName}');
    try {
      jsonResponse = await http.post(
        baseUrl + '/startGame',
        headers: {'Content-type': 'application/json'},
        body: jsonEncode({'pseudo': nickName}),
        encoding: utf8,
      );
    } catch (e) {
      log.e("Error when trying to connect:\n" + e.toString());
      return Response(
        e.toString(),
        400,
      );
    }
    if (jsonResponse.statusCode ==
        200 /* && jsonResponse.body.isNotEmpty() */) {
      return jsonResponse;
    } else {
      return Response(
        'Server unreachable',
        400,
      );
    }
  }

  Future<Response> checkValue(String token, String number) async {
    var jsonResponse;
    log.d('Calling "/checkValue" with parameters: {$token,$number}');
    try {
      jsonResponse = await http.post(
        baseUrl + '/checkValue',
        headers: {'Content-type': 'application/json'},
        body: jsonEncode({'token': token, 'value': number}),
        encoding: utf8,
      );
    } catch (e) {
      log.e("Error when trying to connect:\n" + e.toString());
    }
    if (jsonResponse.statusCode == 200) {
      LinkedHashMap jsonBody = jsonDecode(jsonResponse.body);
      for (MapEntry entry in jsonBody.entries) {
        log.d(entry);
        for (MapEntry nestedEntry in entry.value.entries) {
          log.d(nestedEntry);
          switch (nestedEntry.key) {
            case ('success'):
              {
                if (nestedEntry.value == true)
                  return Response(json.encode(entry.value), 200);
                else
                  return Response('failure', 200);
              }
              break;
            case ('tries'):
              {}
              break;
            case ('indication'):
              {}
              break;
            default:
              {}
              break;
          }
        }
      }
      return jsonResponse;
    } else {
      return Response(
        'Server unreachable',
        jsonResponse.statusCode,
      );
    }
  }
}
