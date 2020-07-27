package ds.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author simont
 *
 */
public class AssignmentMarker {

	private static java.util.ArrayList<Failure> failures;


	private static void testrunner(String name, Class c) {

		Result test = JUnitCore.runClasses(c);
		failures.addAll(test.getFailures());

		for ( int i = 0 ; i < test.getFailures().size() ; ++i ) {
			String testID = test.getFailures().get(i).getDescription().getClassName() + ":" + 
					test.getFailures().get(i).getDescription().getMethodName();
			testID = testID.replaceAll("Test", ""). replaceAll("test", ""); // Strip the word "test" from the identifying string. 
		}
	}

	// Simple test information
	private static void runATest(String name, Class c) {
		System.out.println("\n" + name);
		for ( int i = 0 ; i < name.length() ; ++i ) 
			System.out.print("-");
		System.out.println();

		testrunner(name, c);
	}

	public static void main(String[] args) {

		boolean runList = true;
		boolean runStack = true;
		boolean runQueue = true;
		boolean runEval = true;
		boolean runInfixToPostfix = true;

		failures = new java.util.ArrayList<Failure>();

		float listPass = 0, listLost = 0, queuePass = 0, queueLost = 0, stackPass = 0 , stackLost =0 , infixPass = 0 , infixLost =0 , evalPass = 0 , evalLost =0 ;

		System.out.println("Data Structures Assignment #1:\n\tCollections and Maths.\n");

		System.out.println("-----------------------------");

		if ( runList ) {
			runATest("Testing the List class...", ListTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("List:") )
						gained += Marks.getInstance().marks.get(Marks.getInstance().passed.get(i));
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("List:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				listPass = gained;
				listLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}
		}

		if ( runQueue ) {
			runATest("Testing the Queue class...", QueueTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("Queue:") )
						gained += Marks.getInstance().marks.get(Marks.getInstance().passed.get(i));
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("Queue:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				queuePass = gained;
				queueLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}
		} 


		if ( runStack ) {
			runATest("Testing the Stack class...", StackTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("Stack:") )
						gained += Marks.getInstance().marks.get(Marks.getInstance().passed.get(i));
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("Stack:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				stackPass = gained;
				stackLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}
		}

		if ( runInfixToPostfix ) {

			runATest("Testing the infix-to-postfix conversion...", InfixToPostfixTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("InfixToPostfix:") )
					{
						String s = Marks.getInstance().passed.get(i);
						gained += Marks.getInstance().marks.get(s);
					}
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("InfixToPostfix:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				infixPass = gained;
				infixLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}

		}

		if ( runEval ) {
			runATest("Testing the evaluation of postfix expressions..", EvaluationTests.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("Evaluations:") )
						gained += Marks.getInstance().marks.get(Marks.getInstance().passed.get(i));
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("Evaluations:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				evalPass = gained;
				evalLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}


			System.out.println("-----------------------------");
			System.out.println("\nFailed test details");
			System.out.println("( Test class: test name -> Error details)\n");
			for (Failure failure : failures) {
				String name = failure.getDescription().getClassName().replaceAll("Test",  "") 
						+ ": " +  failure.getDescription().getMethodName();
				System.out.print(name + " -> ");
				if ( failure.getMessage() != null )
					System.out.print(failure.getMessage());
				else
					System.out.print("No failure message present " +
							"(indicates systemic issues somewhere in the codebase)." +
							"\nTrace: " + failure.getTrace());
				System.out.print("\n");
			}

		}
		System.out.println("-----------------------------");
		System.out.println("Mark summary:");
		System.out.println("\tList:  [gained " + listPass + ", lost " + listLost + "]");
		System.out.println("\tQueue:  [gained " + queuePass + ", lost " + queueLost + "]");
		System.out.println("\tStack:  [gained " + stackPass + ", lost " + stackLost + "]");
		System.out.println("\tInfix:  [gained " + infixPass + ", lost " + infixLost + "]");
		System.out.println("\tEvaluation:  [gained " + evalPass + ", lost " + evalLost + "]");

		//		System.out.println("Total: [" + (hP + aP + gP + mP) + ", lost " + (mL + hL + aL + gL) +"] (out of: " + (mP + hP + aP + gP + mL + hL + aL + gL) + ")");
	}

}  
