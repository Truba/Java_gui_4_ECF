package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.param.EntryBlock;

/**
 * Object that executes an action when something happens with the {@link EntryFieldDisplay}.
 * @author Domagoj
 *
 */
public interface IFieldListener {

	/**
	 * Removes a {@link EntryBlock} from the configuration file.
	 * @param field {@link EntryFieldDisplay} to be removed from the configuration file
	 */
	public void removeField(EntryFieldDisplay<?> field);
	
}
