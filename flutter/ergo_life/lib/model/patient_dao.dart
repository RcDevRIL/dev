import 'patient.dart';

class PatientDao{
  static List<Patient> loadPatients(Category category){
    const List<Patient> allPatients = <Patient>[
      Patient(
        category: Category.permanent,
        id: 0,
        name: 'Dupont-0',
        pathology: 'cancer',
        numTel: 'XX.XY.YY.YX.ZZ',
      ),
      Patient(
        category: Category.provisoire,
        id: 1,
        name: 'Dupont-1',
        pathology: 'réeducation  post-op',
        numTel: 'non-renseigné',
      ),
    ];
    if (category == Category.all){
      return allPatients;
    } else {
      return allPatients.where((Patient p) => p.category == category).toList();
    }
  }
}