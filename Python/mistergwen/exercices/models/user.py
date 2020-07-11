class User:
    """ Classe représentant un utilisateur
    """

    def __init__(self, first_name, last_name, job, address):
        self.first_name = first_name
        self.last_name = last_name
        self.job = job
        self.address = address

    def __str__(self):
        no_breaks_adress = self.address.replace("\n", " - ")
        return '{{\n  "First name": "{}",\r\n  "Last name": "{}",\r\n  "Job Title": "{}",\r\n  "Address": "{}"\r\n}}'.format(self.first_name, self.last_name, self.job, no_breaks_adress)

    @property
    def user_name(self):
        return self.first_name + ' ' + self.last_name

    @property
    def user_job(self):
        return self.user_name + " is a " + self.job

    @property
    def user_address(self):
        return self.user_name + " lives at " + self.address
