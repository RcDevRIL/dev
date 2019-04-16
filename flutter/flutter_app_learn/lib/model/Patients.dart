import 'Patient.dart';

class PatientDao {

  static final List<Patient> patients = [
    const Patient(
      id: "1",
      name: "Dupont 1",
      pathologie: "cancer",
      numTel: "XX.XY.YX.YY.XX",
    ),
    const Patient(
      id: "2",
      name: "Dupont 2",
      pathologie: "rééducation post-op",
      numTel: "non renseigné",),
    const Patient(
      id: "3",
      name: "Dupont 3",
      pathologie: "X3-RTH",
      numTel: "XX.XY.YX.YY.XX",),
    const Patient(
      id: "4",
      name: "Dupont 3",
      pathologie: "X3-RTH",
      numTel: "XX.XY.YX.YY.XX",),
    const Patient(
      id: "5",
      name: "Dupont 3",
      pathologie: "X3-RTH",
      numTel: "XX.XY.YX.YY.XX",),
    const Patient(
      id: "6",
      name: "Dupont 3",
      pathologie: "X3-RTH",
      numTel: "XX.XY.YX.YY.XX",),
    const Patient(
      id: "7",
      name: "Dupont 3",
      pathologie: "X3-RTH",
      numTel: "XX.XY.YX.YY.XX",),
    const Patient(
      id: "8",
      name: "Dupont 3",
      pathologie: "X3-RTH",
      numTel: "XX.XY.YX.YY.XX",),

  ];

  static Patient getPatientById(id){
    return patients
        .where((p)=> p.id == id)
        .first;
  }

}