/**
* {@inheritDoc}
* @return True if the given key exists for our context, false otherwise.
*/
@Override
public boolean render(InternalContextAdapter context, Writer writer,
Node node)
throws IOException, ResourceNotFoundException,
ParseErrorException, MethodInvocationException {
	// Key is the first child of our node for the given context.
	String key = String.valueOf(node.jjtGetChild(0).value(context));
	BundleList b = (BundleList)(context.get(BundleList.KEY));
	if (b != null) {
		// Get value from the bundle
		String msg = b.getValue(key);
		// Number of childs / params for our node.
		int params = node.jjtGetNumChildren();
		// Stronger: indexOf('{') != -1 && indexOf('{') < indexOf('}')
		// Still possible to use { & } if the message has no parameter.
		// Call syntax -> #i18n('project.label' ${it.title})
		// Properties syntax : project.label = {0} Project
		if ((params > 1) && (msg.indexOf('{') != -1)) {
			Object[] args = new Object[params - 1];
			//for (int i=0; i<params-1; i++) {
				// args[i] = node.jjtGetChild(i+1).value(context);
				//}
				for (int i=1; i<params; i++) {
					args[i-1] = node.jjtGetChild(i).value(context);
				}
				// Replaces all of the {vars} with their {args}.
				msg = MessageFormat.format(msg, args);

				if (log.isDebugEnabled()) {
					log.debug("#i18n render - args inserted for " + key);
				}
			}
			// Write value where the key was.
			writer.write(msg);
			return true;
		}
		else {
			log.warn("{}: Failed to resolved key \"{}\"" +
				": no bundle defined in template {}",
				this.getName(), key, node.getTemplateName());
			return false;
		}
	}