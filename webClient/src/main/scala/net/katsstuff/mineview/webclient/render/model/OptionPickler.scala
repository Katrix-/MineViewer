package net.katsstuff.mineview.webclient.render.model

import upickle.Js

object OptionPickler extends upickle.AttributeTagged {
	override implicit def OptionW[T: Writer]: Writer[Option[T]] = Writer {
		case None    => Js.Null
		case Some(s) => implicitly[Writer[T]].write(s)
	}

	override implicit def OptionR[T: Reader]: Reader[Option[T]] = Reader {
		case Js.Null     => None
		case v: Js.Value => Some(implicitly[Reader[T]].read.apply(v))
	}
}