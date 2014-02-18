package hr.fer.zemris.ecf.log.genotypes;

import java.lang.reflect.Constructor;

/**
 * This is a static class with one meted and that method is used to create right
 * type (extension) of {@link AbstractGenotype} from given
 * {@link InitialGenotype}.
 * 
 * @version 1.0
 * 
 */
public class GenotypeReader {

	/**
	 * This meted gets the {@link InitialGenotype} and creates accurate
	 * {@link AbstractGenotype} extension from name in InitialGenotype.
	 * 
	 * @param ig
	 *            InitialGenotype given to create correct AbstractGenotype
	 * @return AbstractGenotype extension determined by the name
	 */
	public static AbstractGenotype<?> getGenotype(InitialGenotype ig) {//"hr.fer.zemris.ecf.log.genotypes."+ig.name
		Class<?> myClass;
		Object o = null;
		try {
			myClass = Class.forName("hr.fer.zemris.ecf.log.genotypes."+ig.name);
			Constructor<?> cons = myClass.getConstructor(InitialGenotype.class);
			o = cons.newInstance(ig);
		} catch (Exception e) {
			o = new UnknownGenotype(ig);
		}
		return (AbstractGenotype<?>) o;
	}
	
//	Old version (still here in case new fails):
//	public static AbstractGenotype<?> getGenotype(InitialGenotype ig) {
//
//		switch (ig.name) {
//
//		case "BitString":
//			return new BitString(ig);
//
//		case "Binary":
//			return new Binary(ig);
//
//		case "FloatingPoint":
//			return new FloatingPoint(ig);
//
//		case "Tree":
//			return new Tree(ig);
//
//		case "Permutation":
//			return new Permutation(ig);
//
//		default:
//			throw new IllegalArgumentException("Genotype: " + ig.name
//					+ " has not been implemented.");
//		}
//	}
}
