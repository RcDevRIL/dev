from cmd import Cmd


class Cli(Cmd):
    """Cette classe permet de créer une instance de cli facilement
     et d'y implémenter des commandes
    """

    def __init__(self):
        # Init default cmd prompt
        Cmd.__init__(self)
        # Overrides default properties
        self.doc_header = "Documented commands (type help <command>):"
        self.undoc_header = "Undocumented commands"
        self.prompt = ">>> "
        self.intro = """\nWelcome in this mini CLI project written in Python v3.8.3!!
(type help or ? for commands list)\n"""
        self.ruler = "-"

    def emptyline(self):
        print("Type 'exit' to close CLI or type ? for help.")

    def default(self, line):
        print(f"Unknown syntax : {line} (type help or ? for commands list)\n")
        self.emptyline()

    def do_exit(self, line):
        """Exits from the console"""
        return True

    def do_quit(self, line):
        """Exits from the console"""
        return True

    def do_kill(self, line):
        """Exits from the console"""
        return True

    def do_q(self, line):
        """Exits from the console"""
        return True


class ExtendedCli(Cli):
    """Cette classe héritant de Cli permet de rajouter la commande "greet" à notre CLI
    """

    def do_greet(self, line):
        """Prints "Hello World !" to the console.
        """
        print("\nHello World !\n")
        return False


# Permet de lancer le fichier en mode script
if __name__ == '__main__':
    prompt = ExtendedCli()
    prompt.cmdloop()
