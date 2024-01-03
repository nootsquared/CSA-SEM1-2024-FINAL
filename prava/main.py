import subprocess

def transpile(code):
    replacements = {
        # Class and method declarations
        "thingie": "class",
        "publicstaticthing": "public static void main(String[] args)",

        # Print statements
        "yapbutonanewline": "System.out.println",
        "yap": "System.out.print",

        # Control structures
        "fr": "for",
        "sus" : "if",
        "onelastresort" : "else if",
        "bruh" : "else",
        "wyd" : "while",

        # Return and break statements
        "gimme": "return",
        "backoff": "break",

        # Comparison and equality operators
        "bet": "equals",
        "nah" : "!=",
        "yuh" : "==",

        # Arithmetic operators
        "pluh" : "+",
        "shlawg" : "-",
        "lilbro" : "/",
        "bigbro" : "*",

        # Assignment and boolean operators
        "nocap" : "=",
        "ong" : "True",
        "cap" : "False",

        # Relational operators
        "lit" : "<",
        "bro" : ">",

        # Data types
        "lowkey": "int",
        "highkey" : "String",
        "buffedlowkey" : "double",

        # Scanner methods
        "nextLowkey" : "nextInt",
        "nextHighkey" : "nextLine",

        # String methods
        "subby":"substring",

        # End of statement
        " mk": ";",

    }

    for key, value in replacements.items():
        code = code.replace(key, value)

    return code

def main():
    with open('prava/source.txt', 'r') as f:
        source_code = f.read()

    java_code = transpile(source_code)

    with open('prava/output.java', 'w') as f:
        f.write(java_code)

    # Compile the Java file
    compile_process = subprocess.run(['javac', 'prava/output.java'])
    if compile_process.returncode != 0:
        print('Error compiling Java file')
        return

    # Run the Java file and redirect its output to java_output.txt
    with open('prava/java_output.txt', 'w') as f:
        run_process = subprocess.run(['java', '-cp', 'prava', 'output'], stdout=f)
        if run_process.returncode != 0:
            print('Error running Java file')

if __name__ == "__main__":
    main()