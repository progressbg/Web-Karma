package edu.isi.karma.cleaning;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
public class Interpretor {
	private PyObject interpreterClass;

    public Interpretor() {
        PythonInterpreter interpreter = new PythonInterpreter();
        //change the sys.path
        interpreter.exec("import sys");
        interpreter.exec("sys.path.append('/Users/bowu/Documents/Aptana Studio 3 Workspace/IDCT/')");
        ///Users/bowu/projects/IDCT/src/edu/isi/karma/cleaning
        interpreter.exec("sys.path.append('Users/bowu/projects/IDCT/src/edu/isi/karma/cleaning')");
        interpreter.exec("from FunctionList import *");
        interpreter.exec("from Interpreter import *");
        //interpreter.exec("print sys.path");
        interpreterClass = interpreter.get("Interpreter");
    }

    /**
     * The create method is responsible for performing the actual
     * coercion of the referenced python module into Java bytecode
     */

    public InterpreterType create(String scripts) {

        PyObject buildingObject = interpreterClass.__call__(new PyString(scripts));
        InterpreterType ele =  (InterpreterType)buildingObject.__tojava__(InterpreterType.class);
        return ele;
    }
    public static void main(String[] args)
    {
    		Interpretor it = new Interpretor();
    		String scripts="substr(value,indexOf(value,'START','WRD'),indexOf(value,'WRD','SYB'))+'*'+substr(value,indexOf(value,'SYB','NUM'),indexOf(value,'NUM','END'))";
    		String value = "Dositejeva&nbsp;22";
    		InterpreterType worker = it.create(scripts);
    		System.out.println(worker.execute(value));
    	
    }
}