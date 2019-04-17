import 'package:flutter/foundation.dart';

enum Category {
  all,
  permanent,
  provisoire,
}

class Patient{
  const Patient({
    @required this.id,
    @required this.category,
    @required this.name,
    @required this.pathology,
    @required this.numTel,
})  : assert(id != null),
        assert(category != null),
        assert(name != null),
        assert(pathology != null),
        assert(numTel != null);

  final Category category;
  final int id;
  final String name;
  final String pathology;
  final String numTel;

  String get patientName => name;
  String get patientNumTel => numTel;

  @override
  String toString() => '$name, pathologie : $pathology';
}
