/**
 * Copyright (c) 2009--2010, Stephan Preibisch & Stephan Saalfeld
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.  Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials
 * provided with the distribution.  Neither the name of the Fiji project nor
 * the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * @author Stephan Preibisch & Stephan Saalfeld
 */
package net.imglib2.type.numeric.integer;

import net.imglib2.img.NativeImg;
import net.imglib2.img.NativeImgFactory;
import net.imglib2.img.basictypeaccess.ByteAccess;

public class ByteType extends GenericByteType<ByteType>
{
	// this is the constructor if you want it to read from an array
	public ByteType( final NativeImg<ByteType, ? extends ByteAccess> img ) { super( img ); }
	
	// this is the constructor if you want it to be a variable
	public ByteType( final byte value ) { super( value ); }

	// this is the constructor if you want it to be a variable
	public ByteType() { super( (byte)0 ); }

	@Override
	public NativeImg<ByteType, ? extends ByteAccess> createSuitableNativeImg( final NativeImgFactory<ByteType> storageFactory, final long dim[] )
	{
		// create the container
		final NativeImg<ByteType, ? extends ByteAccess> container = storageFactory.createByteInstance( dim, 1 );
		
		// create a Type that is linked to the container
		final ByteType linkedType = new ByteType( container );
		
		// pass it to the NativeContainer
		container.setLinkedType( linkedType );
		
		return container;
	}
	
	@Override
	public ByteType duplicateTypeOnSameNativeImg() { return new ByteType( img ); }

	public byte get() { return getValue(); }
	public void set( final byte b ) { setValue( b ); }
	
	@Override
	public int getInteger(){ return (int)get(); }
	@Override
	public long getIntegerLong() { return get(); }
	@Override
	public void setInteger( final int f ){ set( (byte)f ); }
	@Override
	public void setInteger( final long f ){ set( (byte)f ); }
	
	@Override
	public double getMaxValue() { return Byte.MAX_VALUE; }
	@Override
	public double getMinValue()  { return Byte.MIN_VALUE; }

	@Override
	public ByteType createVariable(){ return new ByteType( (byte)0 ); }

	@Override
	public ByteType copy(){ return new ByteType( getValue() ); }
}