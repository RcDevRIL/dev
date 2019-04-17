import 'package:scoped_model/scoped_model.dart';

import 'patient.dart';
import 'patient_dao.dart';

class AppStateModel extends Model{

  List<Patient> _filteredPatients;
  Category _selectedCategory = Category.all;
  final Map<int, String> _patientsInBuffer = <int, String>{};

  Map<int, String> get patientsInBuffer => Map<int, String>.from(_patientsInBuffer);

  Category get selectedCategory => _selectedCategory;

  List<Patient> getPatients(){
    if(_filteredPatients == null){
      return <Patient>[];
    }

    if(_selectedCategory == Category.all){
      return List<Patient>.from(_filteredPatients);
    } else{
      return _filteredPatients
          .where((Patient p) => p.category == _selectedCategory)
          .toList();
    }
  }

  void addPatientToBuffer(int patientId){
    if(!_patientsInBuffer.containsKey(patientId)){
      _patientsInBuffer[patientId] = 'Dupont-$patientId';
    } else{
      //TODO: implement alert dialog
    }
    notifyListeners();
  }

  void removePatientFromBuffer(int patientId){
    if(_patientsInBuffer.containsKey(patientId)){
      _patientsInBuffer.remove(patientId);
    } else{
      //TODO: implement alert dialog
    }
  }

  Patient getPatientById(int patientId){
    return _filteredPatients.firstWhere((Patient p) => p.id == patientId);
  }

  void clearBuffer(){
    _filteredPatients.clear();
    notifyListeners();
  }

  void loadPatients(){
    _filteredPatients = PatientDao.loadPatients(Category.all);
    notifyListeners();
  }

  void setCategory(Category newCategory){
    _selectedCategory = newCategory;
    notifyListeners();
  }

}